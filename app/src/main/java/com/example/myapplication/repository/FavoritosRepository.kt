package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Favoritos
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

private const val TAG = "FavoritosRepository"

class FavoritosRepository(private val firestore: FirebaseFirestore) {

    fun buscarFavoritos(): LiveData<List<Favoritos>> {
        val liveData: MutableLiveData<List<Favoritos>> = MutableLiveData()

        firestore.collection("favoritos")
            .get()
            .addOnSuccessListener { snapshot: QuerySnapshot ->
                val favoritos = mutableListOf<Favoritos>()
                for (documento in snapshot.documents) {
                    documento.data?.let { dados ->
                        val praia: String = dados["praia"] as String
                        val favorito = Favoritos(praia = praia)
                        favoritos.add(favorito)
                    }
                }
                liveData.value = favoritos
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Erro ao buscar favoritos: $exception")
                // Aqui você pode tomar alguma ação, como definir um valor de erro na liveData
            }
        return liveData
    }
}
