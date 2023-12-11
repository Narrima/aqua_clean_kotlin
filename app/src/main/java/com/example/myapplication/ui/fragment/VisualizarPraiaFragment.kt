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
import java.util.Locale

class VisualizarPraiaFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentVisualizarPraiaBinding? = null
    private val binding get() = _binding!!

    private var xDelta = 0f
    private var googleMap: GoogleMap? = null

    companion object {
        const val ARG_PRAIA_PESQUISAR = "arg_praia_pesquisar"
        const val ARG_ESTADO_SELECIONADO = "arg_estado_selecionado"
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
            val mapFragment =
                childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            mapFragment?.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        arguments?.getString(ARG_PRAIA_PESQUISAR)?.let { titulo ->
            // Verifica se o estado foi selecionado na tela anterior
            val estadoSelecionado = arguments?.getString(ARG_ESTADO_SELECIONADO)

            // Realiza a geocodificação em uma AsyncTask
            GeocodeAsyncTask().execute(titulo, estadoSelecionado)
        }
    }

    // AsyncTask para realizar a geocodificação em uma thread separada
    @SuppressLint("StaticFieldLeak")
    private inner class GeocodeAsyncTask : AsyncTask<String, Void, Pair<LatLng?, String?>>() {
        override fun doInBackground(vararg params: String): Pair<LatLng?, String?> {
            val address = params[0]
            val estado = params[1]
            val geocoder = Geocoder(requireContext(), Locale("pt", "BR"))

            try {
                // Obtém a lista de endereços usando o nome da praia e o estado
                val results: MutableList<Address>? = geocoder.getFromLocationName("$address, $estado", 1) as? MutableList<Address>
                if (results != null && results.isNotEmpty()) {
                    val latLng = LatLng(results[0].latitude, results[0].longitude)
                    val postalAddress = results[0].getAddressLine(0) // Obtém o endereço postal

                    return Pair(latLng, postalAddress)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return Pair(null, null)
        }

        override fun onPostExecute(result: Pair<LatLng?, String?>) {
            val latLng = result.first
            val postalAddress = result.second

            if (latLng != null) {
                // Move a câmera para a nova posição
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))

                // Adiciona um marcador para a posição
                googleMap?.addMarker(
                    MarkerOptions().position(latLng).title(arguments?.getString(ARG_PRAIA_PESQUISAR))
                )

                // Atualiza o TextView com o endereço postal iniciando com "Endereço: "
                binding.textView2.text = "Endereço: $postalAddress"
            } else {
                // Em caso de falha na geocodificação, pode lidar aqui
            }
        }
    }
}
