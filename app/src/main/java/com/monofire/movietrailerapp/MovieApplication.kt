package com.monofire.movietrailerapp

import android.app.Application
import com.monofire.movietrailerapp.di.appModule
import com.monofire.movietrailerapp.di.networkModule
import com.monofire.movietrailerapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:17          │
//└─────────────────────────────┘
class MovieApplication : Application() {
    private val moduleList: List<Module> = listOf(
        appModule, networkModule, viewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(moduleList)
        }
    }
}