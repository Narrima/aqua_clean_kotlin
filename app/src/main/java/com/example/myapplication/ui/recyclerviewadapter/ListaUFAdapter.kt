package com.example.myapplication.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Estado
import com.example.myapplication.ui.fragment.BuscarPraiaFragment

class ListaUFAdapter(
    private val context: Context,
    private val listaEstados: List<Estado>,
    private val estadoItemClickListener: BuscarPraiaFragment
) : RecyclerView.Adapter<ListaUFAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNomeEstado: TextView = itemView.findViewById(R.id.item_buscar_praia_UF)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_buscar_praia_uf, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estado = listaEstados[position]

        holder.itemView.setOnClickListener {
            estadoItemClickListener.onEstadoItemClick(estado) // Alteração aqui
        }

        holder.textViewNomeEstado.text = estado.nome
    }

    override fun getItemCount(): Int {
        return listaEstados.size
    }
}