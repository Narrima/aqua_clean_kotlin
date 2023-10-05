package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCreateAcconuntBinding
import com.example.myapplication.extensions.snackBar
import com.example.myapplication.model.Usuario

import com.example.myapplication.ui.viewModel.CadastroUsuarioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateAccountFragment : Fragment() {


    private val viewModel: CadastroUsuarioViewModel by viewModel()

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

            limparCampos()

            val email: String = binding.createEmail.editableText.toString()
            val senha = binding.createSenha.editableText.toString()
            val confirma = binding.createConfirmaSenha.editableText.toString()

            val valido = validaCampos(email, senha, confirma)

            if(valido){
                cadastra(Usuario(email, senha))
            }

        }

    }

    private fun cadastra(usuario: Usuario) {
        viewModel.cadastra(usuario).observe(viewLifecycleOwner, Observer {
            it?.let { recurso ->
                if (recurso.dado) {
                    view?.snackBar("Cadastro realizado com sucesso")
                    findNavController().navigate(R.id.nav_login)
                } else {
                    val mensagemErro = recurso.erro ?: "Ocorreu uma falha no cadastro"
                    view?.snackBar(mensagemErro)
                }

            }
        })
    }

    private fun validaCampos(email: String, senha: String, confirma: String): Boolean {
        var valido = true

        if (email.isBlank()) {
            binding.createEmail.error = "O campo e-mail é obrigatório"
            valido = false
        }

        if (senha.isBlank()) {
            binding.createSenha.error = "O campo senha é obrigatório"
            valido = false
        }

        if (senha != confirma) {
            binding.createConfirmaSenha.error = "As senhas não conferem"
            valido = false
        }
        return valido
    }

    private fun limparCampos() {
        binding.createEmail.error = null
        binding.createSenha.error = null
        binding.createSenha.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}