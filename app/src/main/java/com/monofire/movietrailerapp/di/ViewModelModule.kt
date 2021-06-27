package com.monofire.movietrailerapp.di


import com.monofire.movietrailerapp.ui.detail.movie.MovieDetailViewModel
import com.monofire.movietrailerapp.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 16.05.2021 - 16:54          │
//└─────────────────────────────┘
val viewModelModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { MovieDetailViewModel() }
}