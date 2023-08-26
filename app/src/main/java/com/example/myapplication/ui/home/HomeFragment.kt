package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val login_inicial = view.findViewById<Button>(R.id.login_inicial)
        val Create_account = view.findViewById<Button>(R.id.Create_account)

        // Configurar ação de clique para o botão
        login_inicial.setOnClickListener {
            // Navegar para o fragmento de login
            findNavController().navigate(R.id.nav_login)
        }
        // Configurar ação de clique para o botão
        Create_account.setOnClickListener {
            // Navegar para o fragmento de login
            findNavController().navigate(R.id.nav_createAccount)
        }
       return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}