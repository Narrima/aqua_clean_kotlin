package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentListaFavoritosBinding
import com.example.myapplication.ui.recyclerviewadapter.FavoritosAdapter
import com.example.myapplication.ui.viewModel.PerfilUsuarioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val quemLogado: PerfilUsuarioViewModel by viewModel()
    private val perfilUsuarioViewModel: PerfilUsuarioViewModel by viewModel()

    private var _binding: FragmentListaFavoritosBinding? = null
    private val binding get() = _binding!!

    private var emailUsuario: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quemLogado.usuario.observe(viewLifecycleOwner, Observer {
            it?.let { usuario ->
                emailUsuario = usuario.email

                emailUsuario?.let {
                    perfilUsuarioViewModel.verificarECriarDocumento(it)
                    perfilUsuarioViewModel.listaPraias.observe(
                        viewLifecycleOwner,
                        Observer { listaPraias ->
                            // Quando a lista de praias estiver pronta, configure o RecyclerView
                            configurarRecyclerView(listaPraias)
                        })
                }
            }
        })
    }

    private fun configurarRecyclerView(praias: List<String>) {
        val recyclerView: RecyclerView = binding.listaFavoritosRecyclerview
        val layoutManager = LinearLayoutManager(requireContext())

        // Verifica se a lista de praias não é nula e tem pelo menos um item
        if (praias.isNotEmpty()) {
            val adapter = FavoritosAdapter(praias)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        } else {
            // Se a lista estiver vazia, você pode lidar com isso de acordo com sua lógica
            // Por exemplo, exibindo uma mensagem indicando que não há dados.
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaFavoritosBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
