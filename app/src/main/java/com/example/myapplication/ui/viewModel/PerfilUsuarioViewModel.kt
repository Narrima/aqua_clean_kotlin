package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Usuario
import com.example.myapplication.repository.FavoritosRepository
import com.example.myapplication.repository.FirebaseAuthRepository

class PerfilUsuarioViewModel(firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    val usuario: LiveData<Usuario> = firebaseAuthRepository.usuario()

    private val _listaPraias = MutableLiveData<List<String>>()
    val listaPraias: LiveData<List<String>> get() = _listaPraias

    fun verificarECriarDocumento(email: String) {
        FavoritosRepository.verificarECriarDocumento(email)
        lerNomesDasPraias(email) // Chama a função para ler os nomes das praias
    }

    // Função para ler nomes das praias
    private fun lerNomesDasPraias(email: String) {
        FavoritosRepository.lerNomesDasPraias(email)
            .observeForever { listaPraias ->
                _listaPraias.value = listaPraias
            }
    }
}
