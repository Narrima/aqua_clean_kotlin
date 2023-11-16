package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentVisualizarPraiaBinding

class VisualizarPraia : Fragment() {

    private var _binding: FragmentVisualizarPraiaBinding? = null
    private val binding get() = _binding!!

    private var xDelta = 0f

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVisualizarPraiaBinding.inflate(inflater, container, false)
        val view = binding.root

        val movableButton = binding.localEstrela

        val layoutParams = movableButton.layoutParams as ViewGroup.MarginLayoutParams

        movableButton.setOnTouchListener { view, event ->
            val x = event.rawX

            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    xDelta = x - layoutParams.leftMargin
                }

                MotionEvent.ACTION_MOVE -> {
                    val newLeftMargin = (x - xDelta).toInt()
                    if (newLeftMargin >= 0) {
                        layoutParams.leftMargin = newLeftMargin
                        view.layoutParams = layoutParams
                    }
                }
            }
            true
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
