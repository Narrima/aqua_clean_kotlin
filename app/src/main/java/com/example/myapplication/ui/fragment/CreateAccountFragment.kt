package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCreateAcconuntBinding

import com.example.myapplication.ui.viewModel.CadastroUsuarioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountFragment : Fragment() {


    private val viewModel: CadastroUsuarioViewModel by viewModel()

//    private lateinit var binding: FragmentCreateAcconuntBinding

    private var _binding: FragmentCreateAcconuntBinding? = null

    //     This property is only valid between onCreateView and
//     onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAcconuntBinding.inflate(inflater, container, false)
        val view = binding.root

//        val create_login = view.findViewById<Button>(R.id.create_login)
//        val login_inicial = view.findViewById<TextView>(R.id.link_login_volta)
//
//        login_inicial.setOnClickListener {
//            // Navegar para o fragmento de login
//            findNavController().navigate(R.id.nav_login)
//        }
//        create_login.setOnClickListener {
//            // Navega para o fragmento de favoritos (R.id.nav_favorites)
//            findNavController().navigate(R.id.nav_login)
//        }

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createLogin.setOnClickListener{
            val email: String = binding.createEmail.editableText.toString()
            val senha = binding.createSenha.editableText.toString()
            viewModel.cadastra(email , senha)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}