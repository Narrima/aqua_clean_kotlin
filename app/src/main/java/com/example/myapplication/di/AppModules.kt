package com.example.myapplication.di

import com.example.myapplication.repository.FavoritosRepository
import com.example.myapplication.repository.FirebaseAuthRepository
import com.example.myapplication.ui.fragment.FavoritesFragment
import com.example.myapplication.ui.viewModel.CadastroUsuarioViewModel
import com.example.myapplication.ui.viewModel.EstadoAppViewModel
import com.example.myapplication.ui.viewModel.LoginViewModel
import com.example.myapplication.ui.viewModel.PerfilUsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    single<FirebaseAuthRepository>{ FirebaseAuthRepository(get()) }
    single<FavoritosRepository>{ FavoritosRepository(get()) }
}

val viewModelModule = module {
    viewModel<CadastroUsuarioViewModel> { CadastroUsuarioViewModel(get()) }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<PerfilUsuarioViewModel> { PerfilUsuarioViewModel(get()) }
    viewModel<EstadoAppViewModel>{ EstadoAppViewModel() } }

val uiModule = module {
    factory<FavoritesFragment> { FavoritesFragment() } }

val firebasemodule = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }
}