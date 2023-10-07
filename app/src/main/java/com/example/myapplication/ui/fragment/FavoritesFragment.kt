package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var isStarSelected = false

        val starButton = root.findViewById<ImageButton>(R.id.starButton)
        starButton.setOnClickListener {
            isStarSelected = !isStarSelected
            updateStarButtonState(starButton, isStarSelected)
        }

        val starButton2 = root.findViewById<ImageButton>(R.id.starButton2)
        starButton2.setOnClickListener {
            isStarSelected = !isStarSelected
            updateStarButtonState(starButton2, isStarSelected)
        }

        val starButton3 = root.findViewById<ImageButton>(R.id.starButton3)
        starButton3.setOnClickListener {
            isStarSelected = !isStarSelected
            updateStarButtonState(starButton3, isStarSelected)
        }

        return root
    }

    private fun updateStarButtonState(starButton: ImageButton, isStarSelected: Boolean) {
        if (isStarSelected) {
            starButton.setImageResource(R.drawable.estrela)
        } else {
            starButton.setImageResource(R.drawable.estrela_favoritada)
        }
    }
}