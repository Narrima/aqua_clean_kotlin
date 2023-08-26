package com.example.myapplication.ui.createAccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentCreateAcconuntBinding
import com.example.myapplication.ui.createAccount.CreateAccountViewModel

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
        val assessmentsViewModel =
            ViewModelProvider(this).get(CreateAccountViewModel::class.java)

        _binding = FragmentCreateAcconuntBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}