package com.monofire.movietrailerapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.monofire.movietrailerapp.data.model.Movie
import com.monofire.movietrailerapp.data.network.service.MovieApi
import com.monofire.movietrailerapp.data.network.service.MoviePagingSource
import kotlinx.coroutines.flow.Flow

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:16          │
//└─────────────────────────────┘
class MovieRepository(private val movieApi: MovieApi) {
    fun fetchSearchMovie(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(movieApi, query) }).flow
    }
}