package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "FavoritosRepository"

class FavoritosRepository(private val firestore: FirebaseFirestore) {

    companion object {
        fun lerNomesDasPraias(email: String): LiveData<List<String>> {
            val result = MutableLiveData<List<String>>()

            val db = Firebase.firestore
            val favoritosRef = db.collection("FAVORITOS").document(email)

            favoritosRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val dadosDoDocumento = documentSnapshot.data

                    val nomesDasPraias = mutableListOf<String>()
                    for (i in 1..5) {
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
                        "estado1" to "",
                        "nota1" to "",
                        "praia2" to "VAZIO",
                        "estado2" to "",
                        "nota2" to "",
                        "praia3" to "VAZIO",
                        "estado3" to "",
                        "nota3" to "",
                        "praia4" to "VAZIO",
                        "estado4" to "",
                        "nota4" to "",
                        "praia5" to "VAZIO",
                        "estado5" to "",
                        "nota5" to ""
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
    }
}
