package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Favoritos
import com.example.myapplication.repository.FavoritosRepository

class FavoritosViewModel(private val repository: FavoritosRepository): ViewModel() {

    fun buscarFavoritos(): LiveData<List<Favoritos>> = repository.buscarFavoritos()
}