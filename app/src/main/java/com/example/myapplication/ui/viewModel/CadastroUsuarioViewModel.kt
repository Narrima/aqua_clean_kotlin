package com.example.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.FirebaseAuthRepository

class CadastroUsuarioViewModel(private val repository: FirebaseAuthRepository): ViewModel() {

    fun cadastra(email: String, senha: String){
        repository.cadastrarUsuario(email, senha)
    }

}