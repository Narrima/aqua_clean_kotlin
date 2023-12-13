package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentVisualizarPraiaBinding
import com.example.myapplication.ui.viewModel.PerfilUsuarioViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException
import java.util.Locale

class VisualizarPraiaFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: PerfilUsuarioViewModel by viewModel()
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

        // Configura o botão móvel
        setupMovableButton()

        // Inicializa o mapa
        initializeMap()

        arguments?.getString(ARG_PRAIA_PESQUISAR)?.let { titulo ->
            binding.nomePraiaVisualizar.text = titulo

            viewModel.usuario.observe(viewLifecycleOwner, Observer { usuario ->
                usuario?.let {
                    val emailUsuario = it.email
                    emailUsuario?.let { email ->
                        // Lógica relacionada ao email, se necessário
                    }
                }
            })

            binding.favoritar.setOnClickListener {
                realizarAcoesQuandoClicar()
            }

            binding.containerVisualizarPraia.visibility = View.VISIBLE
        }
        verificarPraiaNoBancoDeDados()

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
                    // Calcula a diferença entre a posição do toque e a margem atual
                    xDelta = x - layoutParams.leftMargin
                }

                MotionEvent.ACTION_MOVE -> {
                    // Atualiza a margem esquerda conforme o movimento horizontal
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
        if (googleMap == null && binding.containerVisualizarPraia.visibility == View.VISIBLE) {
            val mapFragment =
                childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            mapFragment?.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        arguments?.getString(ARG_PRAIA_PESQUISAR)?.let { titulo ->
            val estadoSelecionado = arguments?.getString(ARG_ESTADO_SELECIONADO)
            GeocodeAsyncTask().execute(titulo, estadoSelecionado)
        }
    }

    private fun showToast(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class GeocodeAsyncTask : AsyncTask<String, Void, Pair<LatLng?, String?>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            showToast("Carregando...")
        }

        override fun doInBackground(vararg params: String): Pair<LatLng?, String?> {
            val address = params[0]
            val estado = params[1]
            val geocoder = Geocoder(requireContext(), Locale("pt", "BR"))

            try {
                val results: MutableList<Address>? =
                    geocoder.getFromLocationName("$address, $estado", 1) as? MutableList<Address>
                if (results != null && results.isNotEmpty()) {
                    val latLng = LatLng(results[0].latitude, results[0].longitude)
                    val postalAddress = results[0].getAddressLine(0)

                    return Pair(latLng, postalAddress)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return Pair(null, null)
        }

        override fun onPostExecute(result: Pair<LatLng?, String?>) {
            showToast("Carregamento concluído")

            val latLng = result.first
            val postalAddress = result.second

            if (latLng != null) {
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))

                googleMap?.addMarker(
                    MarkerOptions().position(latLng).title(arguments?.getString(ARG_PRAIA_PESQUISAR))
                )

                binding.textView2.text = "Endereço: $postalAddress"
            } else {
                // Em caso de falha na geocodificação, lida aqui
            }
        }
    }

    private fun realizarAcoesQuandoClicar() {
        val db = Firebase.firestore
        val favoritosRef = db.collection("FAVORITOS").document(viewModel.usuario.value?.email ?: "")

        fun onPraiaEncontrada(encontrada: Boolean) {
            if (encontrada) {
                try {
                    val emailUsuario = viewModel.usuario.value?.email
                    val nomePraia = arguments?.getString(ARG_PRAIA_PESQUISAR)
                    val estado = arguments?.getString(ARG_ESTADO_SELECIONADO)

                    emailUsuario?.let { email ->
                        nomePraia?.let { praia ->
                            estado?.let { estado ->
                                viewModel.removerPraiaFavorita(email, praia)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Lide com a exceção de alguma forma, se necessário
                }
                verificarPraiaNoBancoDeDados()
            } else {
                try {
                    val emailUsuario = viewModel.usuario.value?.email
                    val nomePraia = arguments?.getString(ARG_PRAIA_PESQUISAR)
                    val estado = arguments?.getString(ARG_ESTADO_SELECIONADO)

                    emailUsuario?.let { email ->
                        nomePraia?.let { praia ->
                            estado?.let { estado ->
                                viewModel.adicionarPraiaFavorita(email, praia, estado)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Lide com a exceção de alguma forma, se necessário
                }
                verificarPraiaNoBancoDeDados()
            }
        }

        fun onErroVerificarPraia(mensagemErro: String) {
            Log.e("VerificacaoPraia", mensagemErro)
        }

        favoritosRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val dados = documentSnapshot.data

                for (i in 1..10) {
                    val nomeCampo = "praia$i"
                    if (dados?.get(nomeCampo) == arguments?.getString(ARG_PRAIA_PESQUISAR)) {
                        onPraiaEncontrada(true)
                        return@addOnSuccessListener
                    }
                }

                onPraiaEncontrada(false)
            } else {
                onErroVerificarPraia("Documento não encontrado para o usuário ${viewModel.usuario.value?.email}.")
            }
        }.addOnFailureListener { e ->
            onErroVerificarPraia("Erro ao verificar documento para praia favorita: $e")
        }





    }

    private fun verificarPraiaNoBancoDeDados() {
        val db = Firebase.firestore
        val favoritosRef = db.collection("FAVORITOS").document(viewModel.usuario.value?.email ?: "")

        fun onPraiaEncontrada(encontrada: Boolean) {
            if (encontrada) {
                binding.favoritar.setBackgroundResource(R.drawable.coracao_cheio)
                Log.d("VerificacaoPraia", "Praia ${arguments?.getString(ARG_PRAIA_PESQUISAR)} encontrada na lista de favoritos.")
            } else {
                binding.favoritar.setBackgroundResource(R.drawable.coracao)
                Log.d("VerificacaoPraia", "Praia ${arguments?.getString(ARG_PRAIA_PESQUISAR)} não encontrada na lista de favoritos.")
            }
        }

        fun onErroVerificarPraia(mensagemErro: String) {
            Log.e("VerificacaoPraia", mensagemErro)
        }

        favoritosRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val dados = documentSnapshot.data

                for (i in 1..10) {
                    val nomeCampo = "praia$i"
                    if (dados?.get(nomeCampo) == arguments?.getString(ARG_PRAIA_PESQUISAR)) {
                        onPraiaEncontrada(true)
                        return@addOnSuccessListener
                    }
                }

                onPraiaEncontrada(false)
            } else {
                onErroVerificarPraia("Documento não encontrado para o usuário ${viewModel.usuario.value?.email}.")
            }
        }.addOnFailureListener { e ->
            onErroVerificarPraia("Erro ao verificar documento para praia favorita: $e")
        }
    }
}
