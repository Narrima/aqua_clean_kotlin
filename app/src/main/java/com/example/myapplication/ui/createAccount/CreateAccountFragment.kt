package com.example.myapplication.ui.createAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCreateAcconuntBinding

class CreateAccountFragment : Fragment() {

    private var _binding: FragmentCreateAcconuntBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_create_acconunt, container, false)

        val create_login = view.findViewById<Button>(R.id.create_login)
        val login_inicial = view.findViewById<TextView>(R.id.link_login_volta)

        login_inicial.setOnClickListener {
            // Navegar para o fragmento de login
            findNavController().navigate(R.id.nav_login)
        }
        create_login.setOnClickListener {
            // Navega para o fragmento de favoritos (R.id.nav_favorites)
            findNavController().navigate(R.id.nav_login)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}