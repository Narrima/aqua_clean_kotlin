package com.example.myapplication.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import java.lang.Exception

private val TAG = "FirebaseAuthRepository"

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun cadastrarUsuario(usuario: Usuario): LiveData<Resource<Boolean>> {
        val livedata = MutableLiveData<Resource<Boolean>>()
        try {
            val tarefa: Task<AuthResult> = firebaseAuth.createUserWithEmailAndPassword(usuario.email, usuario.senha)

            tarefa.addOnSuccessListener {
                Log.i(TAG, "Cadastra: Cadastro sucedido")
                livedata.value = Resource(true)
            }
            tarefa.addOnFailureListener {exception ->
                Log.i(TAG, "Cadastra: Cadastro falhou", exception)
                val mensagemErro: String = retornaErrosdeCadastro(exception)
                livedata.value = Resource(false, mensagemErro)
            }
        } catch (e: IllegalArgumentException) {
            livedata.value = Resource(false, "E-mail e senha não pode ser vazio")
        }
        return livedata
    }

    private fun retornaErrosdeCadastro(exception: Exception): String {
        val mensagemErro: String = when (exception) {
            is FirebaseAuthWeakPasswordException -> "Senha precisa de pelo menos 6 dígitos"
            is FirebaseAuthInvalidCredentialsException -> "E-mail inválido"
            is FirebaseAuthUserCollisionException -> "E-mail já cadastrado"
            //buscar e cadastrar mais opções de exceptions do firebase
            else -> "Erro genérico"
        }
        return mensagemErro
    }

    fun estalogado(): Boolean {
        val usuarioFirebase: FirebaseUser? = firebaseAuth.currentUser
        if (usuarioFirebase != null) {
            return true
        }
        return false
    }


    fun fazerLogout() {
        firebaseAuth.signOut()
    }

    fun autentica(usuario: Usuario) : LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>()
        firebaseAuth.signInWithEmailAndPassword(usuario.email, usuario.senha)
            .addOnCompleteListener {  tarefa ->
                if(tarefa.isSuccessful){
                    liveData.value = Resource(true)
                } else {
                    Log.e(TAG, "autentica: ", tarefa.exception)
                    val mensagemErro: String = when(tarefa.exception){
                        is FirebaseAuthInvalidUserException -> "E-mail inválido"
                        is FirebaseAuthInvalidCredentialsException -> "E-mail ou senha inválidos"
                        else -> "Erro desconhecido"
                    }
                    liveData.value = Resource(false, mensagemErro)
                }

            }
        return liveData
    }


}