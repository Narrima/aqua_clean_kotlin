package com.example.myapplication.ui.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class FavoritosAdapter(private val praias: List<String>) : RecyclerView.Adapter<FavoritosAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomePraiaTextView: TextView = itemView.findViewById(R.id.item_favoritos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_favoritos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val praia = praias[position]
        holder.nomePraiaTextView.text = praia
        // Configurar outros elementos conforme necessário
    }

    override fun getItemCount(): Int {
        return praias.size
    }
}
