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
    // Remova a propriedade listaLayout se não estiver sendo usada
    // private lateinit var listaLayout: EditText

    private lateinit var editTextPesquisa: EditText
    private var isListaVisivel = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_buscar_praia, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_buscar_praia_recyclerview)
        editTextPesquisa = view.findViewById(R.id.pesquisar)

        // Criando dados de exemplo (substitua isso com seus próprios dados)
        val listaDeDados = listOf(
            Praia("Nome da Praia 1"),
            Praia("Nome da Praia 2")
            // Adicione mais itens conforme necessário
        )

        // Criando e configurando o adaptador
        val adapter = BuscarAdapter(listaDeDados, requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Adicione um TextWatcher para monitorar as alterações no EditText
        editTextPesquisa.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                // Certifique-se de que a função de atualização é chamada
                adapter.atualizarListaComNomesAleatorios(editTextPesquisa.text.toString())
            }
        })

        return view
    }
}
