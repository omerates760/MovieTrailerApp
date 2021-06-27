package com.monofire.movietrailerapp.di

import com.monofire.movietrailerapp.data.repository.MovieRepository
import org.koin.dsl.module

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 16.05.2021 - 16:54          │
//└─────────────────────────────┘
val appModule = module {
    single { MovieRepository(get()) }
}