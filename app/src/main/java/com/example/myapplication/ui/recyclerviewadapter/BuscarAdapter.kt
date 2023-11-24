package com.example.myapplication.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentItemBuscarPraiaBinding
import com.example.myapplication.model.Praia

class BuscarAdapter(
    private val context: Context,
    private var listaDeDados: MutableList<Praia> = mutableListOf()
) : RecyclerView.Adapter<BuscarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o layout do item de busca da praia
        val binding = FragmentItemBuscarPraiaBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: FragmentItemBuscarPraiaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Associa os dados ao layout do item de busca da praia
        fun vincula(buscarPraia: Praia) {
            binding.itemBuscarPraia.text = buscarPraia.pesquisar
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Obtém o item da lista na posição atual
        val item = listaDeDados[position]
        // Define o texto do item de busca da praia com o nome da praia
        holder.vincula(item)
    }

    override fun getItemCount(): Int = listaDeDados.size

    // Atualiza a lista de praias com base no texto atual
    fun atualizaListaComTextoAtual(listaCompleta: List<Praia>, textoAtual: String) {
        // Reinicializa a lista para a lista original
        listaDeDados = mutableListOf<Praia>().apply {
            addAll(listaCompleta)
        }

        // Filtra a lista de praias com base no texto atual
        listaDeDados = listaDeDados.filter { it.pesquisar.contains(textoAtual, ignoreCase = true) }.toMutableList()
        notifyDataSetChanged()
    }

    companion object {
        // Lista original de praias
        private lateinit var listaDePraiasOriginal: List<Praia>
    }
}
