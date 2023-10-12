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
    private var isStarSelected1 = true
    private var isStarSelected2 = true
    private var isStarSelected3 = true

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

        val starButton1 = root.findViewById<ImageButton>(R.id.starButton)
        val starButton2 = root.findViewById<ImageButton>(R.id.starButton2)
        val starButton3 = root.findViewById<ImageButton>(R.id.starButton3)

        starButton1.setOnClickListener {
            isStarSelected1 = !isStarSelected1
            updateStarButtonState(starButton1, isStarSelected1)
        }

        starButton2.setOnClickListener {
            isStarSelected2 = !isStarSelected2
            updateStarButtonState(starButton2, isStarSelected2)
        }

        starButton3.setOnClickListener {
            isStarSelected3 = !isStarSelected3
            updateStarButtonState(starButton3, isStarSelected3)
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