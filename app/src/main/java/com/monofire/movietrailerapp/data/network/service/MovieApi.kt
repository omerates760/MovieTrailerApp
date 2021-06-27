package com.monofire.movietrailerapp.data.network.service

import com.monofire.movietrailerapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:14          │
//└─────────────────────────────┘
interface MovieApi {
    @GET("3/search/movie")
    suspend fun fetchSearchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse
}