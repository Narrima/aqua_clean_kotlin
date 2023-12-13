package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "FavoritosRepository"

class FavoritosRepository(private val firestore: FirebaseFirestore) {
    interface AdicaoPraiaCallback {
        fun onPraiaAdicionadaComSucesso()
        fun onErroAdicionarPraia(mensagem: String)
        fun onListaDeFavoritosCheia()
    }
    interface RemocaoPraiaCallback {
        fun onPraiaRemovidaComSucesso()
        fun onPraiaNaoEncontrada()
        fun onErroRemoverPraia(mensagemErro: String)
    }
    interface VerificacaoPraiaCallback {
        fun onPraiaEncontrada(encontrada: Boolean)
        fun onErroVerificarPraia(mensagemErro: String)
    }
    fun adicionarPraiaFavorita(email: String, nomePraia: String, estadoPraia: String, callback: AdicaoPraiaCallback) {
        val db = Firebase.firestore
        val favoritosRef = db.collection("FAVORITOS").document(email)

        // Verifica se o documento existe
        favoritosRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val dados = documentSnapshot.data
                var praiaJaAdicionada = false

                // Verifica se a praia já foi adicionada
                for (i in 1..10) {
                    val nomeCampo = "praia$i"
                    val estadoCampo = "estado$i"

                    if (dados?.get(nomeCampo) == nomePraia) {
                        // A praia já foi adicionada, chama o callback informando sucesso
                        praiaJaAdicionada = true
                        callback.onPraiaAdicionadaComSucesso()
                        break
                    }
                }

                if (!praiaJaAdicionada) {
                    // Procura por um campo nulo ou com texto "VAZIO" em até 10 praias
                    for (i in 1..10) {
                        val nomeCampo = "praia$i"
                        val estadoCampo = "estado$i"

                        if (dados?.get(nomeCampo) == null || dados.get(nomeCampo) == "VAZIO") {
                            // Campo encontrado, atualiza os dados
                            val novoDados = hashMapOf<String, Any>(
                                nomeCampo to nomePraia,
                                estadoCampo to estadoPraia,
                                "nota$i" to "" // Você pode adicionar outros campos se necessário
                            )

                            favoritosRef.update(novoDados)
                                .addOnSuccessListener {
                                    callback.onPraiaAdicionadaComSucesso()
                                }
                                .addOnFailureListener { e ->
                                    callback.onErroAdicionarPraia("Erro ao adicionar praia como favorita: $e")
                                }

                            break
                        }
                    }
                }
            } else {
                // Documento não existe, criar um novo
                verificarECriarDocumento(email)
                callback.onErroAdicionarPraia("Documento não encontrado para o usuário $email. Um novo documento foi criado.")
            }
        }.addOnFailureListener { e ->
            callback.onErroAdicionarPraia("Erro ao verificar documento para adicionar praia favorita: $e")
        }
    }
    fun removerPraiaFavorita(email: String, nomePraia: String, callback: RemocaoPraiaCallback) {
        val db = Firebase.firestore
        val favoritosRef = db.collection("FAVORITOS").document(email)

        favoritosRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val dados = documentSnapshot.data
                var praiaRemovida = false

                // Verifica se a praia está na lista e remove
                for (i in 1..10) {
                    val nomeCampo = "praia$i"

                    if (dados?.get(nomeCampo) == nomePraia) {
                        // A praia foi encontrada, remove os dados
                        val novoDados = hashMapOf<String, Any>(
                            nomeCampo to "VAZIO",
                            "estado$i" to "VAZIO",
                            "nota$i" to "VAZIO" // Atualize conforme necessário
                        )

                        favoritosRef.update(novoDados)
                            .addOnSuccessListener {
                                callback.onPraiaRemovidaComSucesso()
                            }
                            .addOnFailureListener { e ->
                                callback.onErroRemoverPraia("Erro ao remover praia favorita: $e")
                            }

                        praiaRemovida = true
                        break
                    }
                }

                if (!praiaRemovida) {
                    // A praia não foi encontrada na lista
                    callback.onPraiaNaoEncontrada()
                }
            } else {
                // Documento não existe
                callback.onErroRemoverPraia("Documento não encontrado para o usuário $email.")
            }
        }.addOnFailureListener { e ->
            callback.onErroRemoverPraia("Erro ao verificar documento para remover praia favorita: $e")
        }
    }
    fun verificarPraiaNoBancoDeDados(emailUsuario: String, nomePraia: String, callback: VerificacaoPraiaCallback) {
        val db = Firebase.firestore
        val favoritosRef = db.collection("FAVORITOS").document(emailUsuario)

        favoritosRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val dados = documentSnapshot.data

                // Verifica se a praia está na lista
                for (i in 1..10) {
                    val nomeCampo = "praia$i"

                    if (dados?.get(nomeCampo) == nomePraia) {
                        // A praia foi encontrada na lista
                        callback.onPraiaEncontrada(true)
                        return@addOnSuccessListener
                    }
                }

                // A praia não foi encontrada na lista
                callback.onPraiaEncontrada(false)
            } else {
                // Documento não existe
                callback.onErroVerificarPraia("Documento não encontrado para o usuário $emailUsuario.")
            }
        }.addOnFailureListener { e ->
            callback.onErroVerificarPraia("Erro ao verificar documento para praia favorita: $e")
        }
    }
    companion object {
        fun lerNomesDasPraias(email: String): LiveData<List<String>> {
            val result = MutableLiveData<List<String>>()

            val db = Firebase.firestore
            val favoritosRef = db.collection("FAVORITOS").document(email)

            favoritosRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val dadosDoDocumento = documentSnapshot.data

                    val nomesDasPraias = mutableListOf<String>()
                    for (i in 1..10) {
                        var nomeDaPraia = dadosDoDocumento?.get("praia$i") as? String
                        nomeDaPraia?.let {
                            nomesDasPraias.add(it)
                            Log.d("FavoritesFragment", "Praia $i: $it") // Adiciona log para cada praia lida
                        }
                    }

                    result.postValue(nomesDasPraias)
                } else {
                    result.postValue(emptyList())
                }
            }.addOnFailureListener { e ->
                result.postValue(emptyList())
                Log.e("FavoritesFragment", "Erro ao ler nomes das praias: $e")
            }

            return result
        }

        fun verificarECriarDocumento(email: String) {
            val db = Firebase.firestore
            val favoritosRef = db.collection("FAVORITOS").document(email)

            favoritosRef.get().addOnSuccessListener { documentSnapshot ->
                if (!documentSnapshot.exists()) {
                    // Documento não existe, cria o documento com 15 campos vazios
                    val dadosIniciais = hashMapOf(
                        "praia1" to "VAZIO",
                        "estado1" to "VAZIO",
                        "nota1" to "VAZIO",
                        "praia2" to "VAZIO",
                        "estado2" to "VAZIO",
                        "nota2" to "VAZIO",
                        "praia3" to "VAZIO",
                        "estado3" to "VAZIO",
                        "nota3" to "VAZIO",
                        "praia4" to "VAZIO",
                        "estado4" to "VAZIO",
                        "nota4" to "VAZIO",
                        "praia5" to "VAZIO",
                        "estado5" to "VAZIO",
                        "nota5" to "VAZIO",
                        "praia6" to "VAZIO",
                        "estado6" to "VAZIO",
                        "nota6" to "VAZIO",
                        "praia7" to "VAZIO",
                        "estado7" to "VAZIO",
                        "nota7" to "VAZIO",
                        "praia8" to "VAZIO",
                        "estado8" to "VAZIO",
                        "nota8" to "VAZIO",
                        "praia9" to "VAZIO",
                        "estado9" to "VAZIO",
                        "nota9" to "VAZIO",
                        "praia10" to "VAZIO",
                        "estado10" to "VAZIO",
                        "nota10" to "VAZIO"
                    )

                    favoritosRef.set(dadosIniciais)
                        .addOnSuccessListener {
                            Log.d("FavoritesFragment", "Documento criado com sucesso!")
                        }
                        .addOnFailureListener { e ->
                            Log.e("FavoritesFragment", "Erro ao criar documento: $e")
                        }
                }
            }.addOnFailureListener { e ->
                Log.e("FavoritesFragment", "Erro ao verificar e criar documento: $e")
            }
        }
        fun lerNomesDasPraiasEEstados(email: String): LiveData<List<Pair<String, String>>> {
            val result = MutableLiveData<List<Pair<String, String>>>()

            val db = Firebase.firestore
            val favoritosRef = db.collection("FAVORITOS").document(email)

            favoritosRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val dadosDoDocumento = documentSnapshot.data

                    val nomesDasPraiasEEstados = mutableListOf<Pair<String, String>>()
                    for (i in 1..5) {
                        var nomeDaPraia = dadosDoDocumento?.get("praia$i") as? String
                        var estadoDaPraia = dadosDoDocumento?.get("estado$i") as? String

                        nomeDaPraia?.let {
                            estadoDaPraia?.let {
                                nomesDasPraiasEEstados.add(Pair(it, estadoDaPraia))
                                Log.d("FavoritesFragment", "Praia $i: $it, Estado $i: $estadoDaPraia")
                            }
                        }
                    }

                    result.postValue(nomesDasPraiasEEstados)
                } else {
                    result.postValue(emptyList())
                }
            }.addOnFailureListener { e ->
                result.postValue(emptyList())
                Log.e("FavoritesFragment", "Erro ao ler nomes das praias e estados: $e")
            }

            return result
        }
    }
}
