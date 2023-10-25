package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Favoritos
import com.example.myapplication.repository.FavotirosRepository

class FavoritosViewModel(private val repository: FavotirosRepository): ViewModel() {

    fun buscarFavoritos(): LiveData<List<Favoritos>> = repository.buscarFavoritos()
}