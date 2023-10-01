package com.example.myapplication.repository


import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private val TAG = "FirebaseAuthRepository"

class FirebaseAuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun cadastrarUsuario(email: String, senha: String) {
        val tarefa: Task<AuthResult> = firebaseAuth.createUserWithEmailAndPassword(email, senha)

        tarefa.addOnSuccessListener {
            Log.i(TAG, "Cadastra: Cadastro sucedido")
        }
        tarefa.addOnFailureListener {
            Log.i(TAG, "Cadastra: Cadastro falhou", it)
        }
    }

    private fun autenticarUsuario(firebaseAuth: FirebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword("suzanateste@tuamaeaquelaursa.com", "teste123")
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }
    private fun verificarSeExisteUsuarioLogado(firebaseAuth: FirebaseAuth) {
        val usuarioFirebase: FirebaseUser? = firebaseAuth.currentUser
        if (usuarioFirebase != null) {

        } else {

        }
    }

    private fun fazerLogout(firebaseAuth: FirebaseAuth) {
        firebaseAuth.signOut()
    }

}