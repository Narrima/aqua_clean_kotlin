package com.example.myapplication

import android.app.Application
import com.example.myapplication.ui.di.daoModule
import com.example.myapplication.ui.di.firebasemodule
import com.example.myapplication.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppAplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@AppAplication)
            modules(
                listOf(
                    daoModule,
                    viewModelModule,
                    firebasemodule
                )
            )
        }
    }

}