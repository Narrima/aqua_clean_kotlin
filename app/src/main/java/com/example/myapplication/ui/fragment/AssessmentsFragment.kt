package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class AssessmentsFragment : Fragment() {
    private lateinit var listaLayout: RelativeLayout // Substitua "RelativeLayout" pelo tipo real
    private var isListaVisivel = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_assessments, container, false)


        return view
    }
}
