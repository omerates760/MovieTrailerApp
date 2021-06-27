package com.monofire.movietrailerapp.data.model

import com.google.gson.annotations.SerializedName

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:12          │
//└─────────────────────────────┘
data class MovieResponse(
    var page: Int = 1,

    val totalResults: Int? = null,

    val total_pages: Int? = null,

    @SerializedName("results")
    val movies: List<Movie>? = null
)