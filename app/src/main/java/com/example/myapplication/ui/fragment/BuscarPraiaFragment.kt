package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Praia
import com.example.myapplication.ui.recyclerviewadapter.BuscarAdapter

class BuscarPraiaFragment : Fragment() {

    private lateinit var editTextPesquisa: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BuscarAdapter

    // Lista original de praias
    private lateinit var listaDePraiasOriginal: List<Praia>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Infla o layout do fragmento de busca de praia
        val view = inflater.inflate(R.layout.fragment_buscar_praia, container, false)

        // Inicializa os componentes da UI
        recyclerView = view.findViewById(R.id.lista_buscar_praia_recyclerview)
        editTextPesquisa = view.findViewById(R.id.pesquisar)

        // Criando dados de exemplo (substitua isso pelos seus próprios dados)
        listaDePraiasOriginal = listOf(
            Praia("Nome da Praia 1"),
            Praia("Nome da Praia 2")
            // Adicione mais itens conforme necessário
        )

        // Criando e configurando o adaptador
        adapter = BuscarAdapter(requireContext(), listaDePraiasOriginal.toMutableList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Adicione um TextWatcher para monitorar as alterações no EditText
        editTextPesquisa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                // Atualiza a lista de praias com base no texto atual
                adapter.atualizaListaComTextoAtual(listaDePraiasOriginal, editTextPesquisa.text.toString())

                // Ajusta a visibilidade do RecyclerView com base no texto na caixa de pesquisa
                recyclerView.visibility = if (editTextPesquisa.text.isNotEmpty()) View.VISIBLE else View.GONE
            }
        })
        return view
    }
}
