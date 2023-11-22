package com.example.myapplication.ui.recyclerviewadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Praia

class BuscarAdapter(private var listaDeDados: List<Praia>, private val context: Context) :
    RecyclerView.Adapter<BuscarAdapter.ViewHolder>() {

    // Método chamado quando o ViewHolder é criado.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla o layout do item da lista.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_buscar_praia, parent, false)
        return ViewHolder(view)
    }

    // Método chamado para associar dados à view.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Obtém o item da lista na posição atual.
        val item = listaDeDados[position]
        // Define o texto do EditText com o nome da praia.
        holder.nomeDaPraiaEditText.setText(item.pesquisar)
    }

    // Método que retorna a quantidade total de itens na lista.
    override fun getItemCount(): Int = listaDeDados.size

    // Método utilizado para atualizar a lista com nomes aleatórios com base no texto atual.
    fun atualizarListaComNomesAleatorios(textoAtual: String) {
        // Gera pares de nomes aleatórios e atualiza a lista.
        val paresDeNomes = gerarParesDeNomesAleatorios(textoAtual)
        listaDeDados = paresDeNomes.map { Praia("${it.first} ${it.second}") }
        // Notifica a RecyclerView que os dados foram alterados.
        notifyDataSetChanged()
    }

    // Método que gera pares de nomes aleatórios com base no texto atual.
    private fun gerarParesDeNomesAleatorios(textoAtual: String): List<Pair<String, String>> {
        // Nomes de exemplo para geração aleatória.
        val primeirosNomes = listOf("Ana", "João", "Maria", "Pedro", "Lucas")
        val segundosNomes = listOf("Silva", "Oliveira", "Santos", "Pereira", "Costa")

        // Gera pares de nomes aleatórios.
        return List(10) {
            Pair(primeirosNomes.random(), segundosNomes.random())
        }
    }

    // ViewHolder que representa cada item na lista.
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // EditText no layout do item.
        val nomeDaPraiaEditText: EditText = itemView.findViewById(R.id.pesquisar)
            ?: EditText(itemView.context)
    }
}
