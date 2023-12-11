package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Estado

class EstadoAppViewModel : ViewModel() {

    // Defina os LiveData necessários para armazenar e observar os dados do estado

    // LiveData para a lista de estados
    private val _listaEstados = MutableLiveData<List<Estado>>()
    val listaEstados: LiveData<List<Estado>> get() = _listaEstados

    // LiveData para o estado selecionado
    private val _estadoSelecionado = MutableLiveData<Estado>()
    val estadoSelecionado: LiveData<Estado> get() = _estadoSelecionado

    // Método para atualizar a lista de estados
    fun atualizarListaEstados(novaLista: List<Estado>) {
        _listaEstados.value = novaLista
    }

    // Método para definir o estado selecionado
    fun definirEstadoSelecionado(estado: Estado) {
        _estadoSelecionado.value = estado
    }
}
