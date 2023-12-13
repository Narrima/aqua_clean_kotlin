package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.ui.viewModel.PerfilUsuarioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel : PerfilUsuarioViewModel by viewModel()

    private var _binding: FragmentProfileBinding? = null

    // Esta propriedade só é válida entre onCreateView e onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding  = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.usuario.observe(viewLifecycleOwner) {
            it?.let { usuario ->
                binding.meuPerfilEmail.text = usuario.email
                binding.meuPerfilNomeUsuario.text = usuario.nome
                binding.meuPerfilAtivoDesde.text = usuario.data
            }
        }
    }
}