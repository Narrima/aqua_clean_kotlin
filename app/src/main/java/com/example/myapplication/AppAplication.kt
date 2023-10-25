package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.daoModule
import com.example.myapplication.di.firebasemodule
import com.example.myapplication.di.uiModule
import com.example.myapplication.di.viewModelModule
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
                    uiModule,
                    firebasemodule
                )
            )
        }
    }

}