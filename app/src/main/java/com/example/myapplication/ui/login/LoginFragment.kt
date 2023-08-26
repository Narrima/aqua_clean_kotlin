package com.example.myapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // Esta propriedade só é válida entre onCreateView e onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val logando = view.findViewById<Button>(R.id.logando)
        val link_create = view.findViewById<TextView>(R.id.link_create)
        val link_recuperar_senha = view.findViewById<TextView>(R.id.link_recuperar_senha)

        // Configura ação de clique para o botão "logando"
        logando.setOnClickListener {
            // Navega para o fragmento de favoritos (R.id.nav_favorites)
            findNavController().navigate(R.id.nav_favorites)
        }

        // Configura ação de clique para o botão "link_create"
        link_create.setOnClickListener {
            // Navega para o fragmento de criar conta (R.id.nav_createAccount)
            findNavController().navigate(R.id.nav_createAccount)
        }

        // Configura ação de clique para o botão "link_create"
        link_recuperar_senha.setOnClickListener {
            // Navega para o fragmento de criar conta (R.id.nav_createAccount)
            findNavController().navigate(R.id.nav_reset_password)
        }


        // Retorna a raiz do layout como a vista do fragmento
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
