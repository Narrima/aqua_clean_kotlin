package com.example.myapplication.ui.di

import com.example.myapplication.repository.FirebaseAuthRepository
import com.example.myapplication.ui.viewModel.CadastroUsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    single<FirebaseAuthRepository>{ FirebaseAuthRepository(get()) }
}

val viewModelModule = module {
    viewModel<CadastroUsuarioViewModel> { CadastroUsuarioViewModel(get()) }
}

val firebasemodule = module {
    single<FirebaseAuth> { Firebase.auth }
}