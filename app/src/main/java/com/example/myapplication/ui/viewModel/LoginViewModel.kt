package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Usuario
import com.example.myapplication.repository.FirebaseAuthRepository
import com.example.myapplication.repository.Resource

class LoginViewModel(private val firebaseAuthRepository: FirebaseAuthRepository): ViewModel() {

    fun autenticaUsuario(usuario: Usuario) : LiveData<Resource<Boolean>> {
        return firebaseAuthRepository.autentica(usuario)
    }

    fun desloga(){
        firebaseAuthRepository.fazerLogout()
    }

    fun estalogado(): Boolean = firebaseAuthRepository.estalogado()

    fun naoEstalogado(): Boolean = !estalogado()
}