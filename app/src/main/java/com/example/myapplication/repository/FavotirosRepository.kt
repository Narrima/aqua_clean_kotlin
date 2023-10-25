package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Favoritos
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

private const val TAG = "FavoritosRepository"
class FavotirosRepository(private val firestore: FirebaseFirestore) {

    fun buscarFavoritos(): LiveData<List<Favoritos>> {
        val liveData: MutableLiveData<List<Favoritos>> = MutableLiveData<List<Favoritos>>()
        firestore.collection("favoritos")
            .get()
            .addOnSuccessListener {
                it?.let { snapshot: QuerySnapshot ->
                    val favoritos = mutableListOf<Favoritos>()
                    for (documento in snapshot.documents) {
                        Log.i(TAG, "onCreate: favoritos ${documento.data}")
                        documento.data?.let { dados ->
                            val praia: String = dados["praia"] as String
                            val favorito = Favoritos(praia = praia)
                            favoritos.add(favorito)
                        }
                    }
                    liveData.value = favoritos
                }
            }
            .addOnFailureListener {
                Log.e(TAG, "onCrate: Usuário não logado")
            }
        return liveData
    }
}