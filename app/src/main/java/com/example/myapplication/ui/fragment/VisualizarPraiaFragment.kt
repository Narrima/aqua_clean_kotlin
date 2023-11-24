package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentVisualizarPraiaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class VisualizarPraiaFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentVisualizarPraiaBinding? = null
    private val binding get() = _binding!!

    private var xDelta = 0f
    private var googleMap: GoogleMap? = null

    companion object {
        const val ARG_PRAIA_PESQUISAR = "praia_titulo"

        fun newInstance(pesquisar: String): VisualizarPraiaFragment {
            val fragment = VisualizarPraiaFragment()
            val args = Bundle()
            args.putString(ARG_PRAIA_PESQUISAR, pesquisar)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVisualizarPraiaBinding.inflate(inflater, container, false)
        val view = binding.root

        setupMovableButton()
        initializeMap()

        arguments?.getString(ARG_PRAIA_PESQUISAR)?.let { titulo ->
            binding.nomePraiaVisualizar.text = titulo
        }

        // Exibe o contêiner do mapa quando necessário
        binding.containerVisualizarPraia.visibility = View.VISIBLE

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        initializeMap()
    }

    private fun setupMovableButton() {
        val movableButton = binding.localEstrela
        val layoutParams = movableButton.layoutParams as ViewGroup.MarginLayoutParams

        movableButton.setOnTouchListener { _, event ->
            val x = event.rawX

            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    xDelta = x - layoutParams.leftMargin
                }

                MotionEvent.ACTION_MOVE -> {
                    val newLeftMargin = (x - xDelta).toInt()
                    if (newLeftMargin >= 0) {
                        layoutParams.leftMargin = newLeftMargin
                        movableButton.layoutParams = layoutParams
                    }
                }
            }
            true
        }
    }

    private fun initializeMap() {
        // Verifica se o mapa já foi inicializado e se o contêiner do mapa é visível
        if (googleMap == null && binding.containerVisualizarPraia.visibility == View.VISIBLE) {
            // Inicializa o SupportMapFragment
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            mapFragment?.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        arguments?.getString(ARG_PRAIA_PESQUISAR)?.let { titulo ->
            // Realiza a geocodificação em uma AsyncTask
            GeocodeAsyncTask().execute(titulo)
        }
    }

    // AsyncTask para realizar a geocodificação em uma thread separada
    @SuppressLint("StaticFieldLeak")
    private inner class GeocodeAsyncTask : AsyncTask<String, Void, LatLng?>() {
        override fun doInBackground(vararg params: String): LatLng? {
            val address = params[0]
            val geocoder = Geocoder(requireContext())

            try {
                val results: MutableList<Address>? = geocoder.getFromLocationName(address, 1) as? MutableList<Address>
                if (results != null && results.isNotEmpty()) {
                    return LatLng(results[0].latitude, results[0].longitude)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(latLng: LatLng?) {
            if (latLng != null) {
                // Move a câmera para a nova posição
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))

                // Adiciona um marcador para a posição
                googleMap?.addMarker(MarkerOptions().position(latLng).title(arguments?.getString(ARG_PRAIA_PESQUISAR)))
            } else {
                // Em caso de falha na geocodificação, pode lidar aqui
            }
        }
    }
}
