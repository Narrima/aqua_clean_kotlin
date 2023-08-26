package com.example.myapplication.ui.resetPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentResetPasswordBinding

class ResetPassword: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reset_password, container, false)

        val subescrever_senha = view.findViewById<Button>(R.id.subescrever_senha)

        // Configurar ação de clique para o botão
        subescrever_senha.setOnClickListener {
            // Navegar para o fragmento de login
            findNavController().navigate(R.id.nav_new_password)
        }

        return view
    }

}