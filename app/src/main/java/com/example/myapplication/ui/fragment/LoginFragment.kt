package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.extensions.snackBar
import com.example.myapplication.model.Usuario
import com.example.myapplication.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel : LoginViewModel by viewModel()

    private var _binding: FragmentLoginBinding? = null

    // Esta propriedade só é válida entre onCreateView e onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        val senhaEditText = view.findViewById<EditText>(R.id.login_senha)
        val showPasswordButton = view.findViewById<ImageButton>(R.id.show_password_login)

        var isPasswordVisible = false

        fun togglePassword() {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                senhaEditText.transformationMethod = null // Torna a senha visível
                showPasswordButton.setImageResource(R.drawable.olho_aberto) // Atualiza o ícone do primeiro botão
            } else {
                senhaEditText.transformationMethod = PasswordTransformationMethod() // Oculta a senha
                showPasswordButton.setImageResource(R.drawable.olho_fechado) // Atualiza o ícone do primeiro botão
            }
        }
        showPasswordButton.setOnClickListener { togglePassword() }


//        val logando = view.findViewById<Button>(R.id.logando)
            val link_create = view.findViewById<TextView>(R.id.link_create)
//        val link_recuperar_senha = view.findViewById<TextView>(R.id.link_recuperar_senha)
//
//        // Configura ação de clique para o botão "logando"
//        logando.setOnClickListener {
//            // Navega para o fragmento de favoritos (R.id.nav_favorites)
//            findNavController().navigate(R.id.nav_favorites)
//        }
//
        // Configura ação de clique para o botão "link_create"
        link_create.setOnClickListener {
          //  Navega para o fragmento de criar conta
           findNavController().navigate(R.id.nav_createAccount)
        }
//
//        // Configura ação de clique para o botão "link_create"
//        link_recuperar_senha.setOnClickListener {
//            // Navega para o fragmento de criar conta (R.id.nav_createAccount)
//            findNavController().navigate(R.id.nav_reset_password)
//        }


        // Retorna a raiz do layout como a vista do fragmento
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logando.setOnClickListener{

            val email: String = binding.loginUsuario.editableText.toString()
            val senha: String = binding.loginSenha.editableText.toString()

            viewModel.autenticaUsuario(Usuario(email, senha)).observe(viewLifecycleOwner, Observer {
                it?.let {recurso ->
                    if(recurso.dado){
                        getView()?.snackBar("Login realizado com sucesso")
                        findNavController().navigate(R.id.nav_favorites)
                    } else {
                        val mensagemErro = recurso.erro ?: "Erro durante a autenticação"
                        getView()?.snackBar(mensagemErro)
                    }
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
