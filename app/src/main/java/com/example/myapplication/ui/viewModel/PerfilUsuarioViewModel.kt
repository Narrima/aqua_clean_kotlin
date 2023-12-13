package com.example.myapplication.ui.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Usuario
import com.example.myapplication.repository.FavoritosRepository
import com.example.myapplication.repository.FirebaseAuthRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PerfilUsuarioViewModel(firebaseAuthRepository: FirebaseAuthRepository) : ViewModel() {

    val usuario: LiveData<Usuario> = firebaseAuthRepository.usuario()

    private val _listaPraias = MutableLiveData<List<String>>()
    val listaPraias: LiveData<List<String>> get() = _listaPraias

    private val _listaPraiasEEstados = MutableLiveData<List<Pair<String, String>>>()


    fun verificarECriarDocumento(email: String) {
        FavoritosRepository.verificarECriarDocumento(email)
        lerNomesDasPraias(email) // Chama a função para ler os nomes das praias
        lerNomesDasPraiasEEstados(email) // Chama a função para ler os nomes das praias e estados
    }

    // Função para ler nomes das praias
    private fun lerNomesDasPraias(email: String) {
        FavoritosRepository.lerNomesDasPraias(email)
            .observeForever { listaPraias ->
                _listaPraias.value = listaPraias
            }
    }

    // Função para ler nomes das praias e estados
    private fun lerNomesDasPraiasEEstados(email: String) {
        FavoritosRepository.lerNomesDasPraiasEEstados(email)
            .observeForever { listaPraiasEEstados ->
                _listaPraiasEEstados.value = listaPraiasEEstados
            }
    }
    fun adicionarPraiaFavorita(emailUsuario: String, nomePraia: String, estado: String) {
        // Aqui você pode chamar a função do repositório para adicionar a praia como favorita
        val favoritosRepository = FavoritosRepository(Firebase.firestore)

        // Adiciona a praia favorita e executa a lógica após a conclusão
        favoritosRepository.adicionarPraiaFavorita(emailUsuario, nomePraia, estado, object :
            FavoritosRepository.AdicaoPraiaCallback {
            override fun onPraiaAdicionadaComSucesso() {
                // Atualiza as listas após adicionar a praia favorita (se necessário)
                lerNomesDasPraias(emailUsuario)
                lerNomesDasPraiasEEstados(emailUsuario)
            }

            override fun onErroAdicionarPraia(mensagem: String) {
                // Lida com o erro, se necessário
                Log.e(TAG, mensagem)
            }

            override fun onListaDeFavoritosCheia() {
                // Lida com a lista de favoritos cheia, se necessário
                Log.d(TAG, "Lista de favoritos cheia.")
            }
        })
    }
    fun removerPraiaFavorita(emailUsuario: String, nomePraia: String) {
        // Chame a função do repositório para remover a praia como favorita
        val favoritosRepository = FavoritosRepository(Firebase.firestore)

        // Remove a praia favorita e executa a lógica após a conclusão
        favoritosRepository.removerPraiaFavorita(emailUsuario, nomePraia, object :
            FavoritosRepository.RemocaoPraiaCallback {
            override fun onPraiaRemovidaComSucesso() {
                // Atualiza as listas após remover a praia favorita (se necessário)
                lerNomesDasPraias(emailUsuario)
                lerNomesDasPraiasEEstados(emailUsuario)
            }

            override fun onPraiaNaoEncontrada() {
                // Lida com a situação em que a praia não foi encontrada na lista de favoritos
                Log.d(TAG, "Praia não encontrada na lista de favoritos.")
            }

            override fun onErroRemoverPraia(mensagem: String) {
                // Lida com o erro ao remover a praia favorita, se necessário
                Log.e(TAG, mensagem)
            }
        })
    }
}
