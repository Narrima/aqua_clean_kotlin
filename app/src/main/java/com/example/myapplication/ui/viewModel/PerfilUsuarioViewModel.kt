package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Usuario
import com.example.myapplication.repository.FirebaseAuthRepository

class PerfilUsuarioViewModel(firebaseAuthRepository: FirebaseAuthRepository): ViewModel() {

    val usuario: LiveData<Usuario> = firebaseAuthRepository.usuario()
}