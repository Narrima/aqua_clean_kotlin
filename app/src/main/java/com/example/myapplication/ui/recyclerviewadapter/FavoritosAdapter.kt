package com.example.myapplication.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentItemFavoritosBinding
import com.example.myapplication.model.Favoritos

class FavoritosAdapter(
    private val context: Context,
    private val favoritos: MutableList<Favoritos> = mutableListOf()
) : RecyclerView.Adapter<FavoritosAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentItemFavoritosBinding.inflate(
            LayoutInflater.from(context)
        )
        return ViewHolder(binding)
    }

    class ViewHolder(binding: FragmentItemFavoritosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val campoFavoritos by lazy { binding.itemFavoritos }

        fun vincula(favorito: Favoritos) {
            campoFavoritos.text = favorito.praia
        }
    }

    override fun getItemCount() = favoritos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorito = favoritos[position]
        holder.vincula(favorito)
    }

    fun atualiza(listaFavoritos: List<Favoritos>) {
        notifyItemRangeRemoved(0, favoritos.size)
        favoritos.clear()
        favoritos.addAll(listaFavoritos)
        notifyItemRangeInserted(0, favoritos.size)
    }

}
