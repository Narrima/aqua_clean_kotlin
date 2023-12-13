package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentResetPasswordBinding
import com.example.myapplication.extensions.snackBar
import com.example.myapplication.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPassword: Fragment() {

    private val viewModel : LoginViewModel by viewModel()

    private var _binding: FragmentResetPasswordBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding  = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.subescreverSenha.setOnClickListener {
            val email : String  = binding.recuperarSenha.editableText.toString()

            viewModel.recuperarSenha(email)

            getView()?.snackBar("Um e-mail de recuperação de senha foi enviado para $email")

            findNavController().navigate(R.id.nav_login)
        }
    }
}