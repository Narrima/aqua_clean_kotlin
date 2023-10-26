package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.databinding.FragmentListaFavoritosBinding
import com.example.myapplication.ui.recyclerviewadapter.FavoritosAdapter
import com.example.myapplication.ui.viewModel.FavoritosViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : Fragment() {

    private val viewModel : FavoritosViewModel by viewModel()
//    @Suppress("DEPRECATION")
//    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()
    private val adapter: FavoritosAdapter by inject()
//    private val controlador by lazy {
//        findNavController()
//    }

    private var _binding: FragmentListaFavoritosBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscarProdutos()
    }

    private fun buscarProdutos() {
        viewModel.buscarFavoritos().observe(this) { favoritosEncontrados ->
            favoritosEncontrados?.let {
                adapter.atualiza(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentListaFavoritosBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        estadoAppViewModel.temComponentes = EstadoAppViewModel.ComponentesVisuais(
//            appBar = false,
//            bottomNavigation = true)
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        val divisor = DividerItemDecoration(context, VERTICAL)
        binding.listaFavoritosRecyclerview.addItemDecoration(divisor)
        binding.listaFavoritosRecyclerview.adapter = adapter
    }

//        private fun updateStarButtonState(starButton: ImageButton, isStarSelected: Boolean) {
//        if (isStarSelected) {
//            starButton.setImageResource(R.drawable.estrela)
//        } else {
//            starButton.setImageResource(R.drawable.estrela_favoritada)
//        }
//    }
}