package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        // Inicializa o SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Configurações do mapa, como tipo de mapa, controles, marcadores, etc.

        // Define o tipo de mapa como MAP_TYPE_NORMAL (mapa de rua)
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        // Move a câmera para exibir o Brasil como um todo
        val brasil = LatLng(-14.235, -51.925)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brasil, 4f)) // O valor 4 define o nível de zoom
    }
}
