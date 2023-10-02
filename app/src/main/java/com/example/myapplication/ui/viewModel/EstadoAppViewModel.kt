package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EstadoAppViewModel : ViewModel() {

    val componentes: LiveData<ComponentesVisuais> get() = comp

    private var comp: MutableLiveData<ComponentesVisuais> =
        MutableLiveData<ComponentesVisuais>().also {
            it.value = temComponentes
        }

    var temComponentes: ComponentesVisuais = ComponentesVisuais()
        set(value){
            field = value
            comp.value = value
        }

    class ComponentesVisuais(
        val appBar: Boolean = false,
        val bottomNavigation: Boolean = false
    )
}