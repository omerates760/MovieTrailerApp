package com.monofire.movietrailerapp.di

import com.monofire.movietrailerapp.BuildConfig
import com.monofire.movietrailerapp.BuildConfig.BASE_URL
import com.monofire.movietrailerapp.data.network.service.MovieApi
import com.monofire.movietrailerapp.data.network.service.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 16.05.2021 - 16:54          │
//└─────────────────────────────┘
val networkModule = module {
    single { createOkHttpClient(tokenInterceptor = get()) }
    single { createRetrofit(get()) }
    factory { createWebService<MovieApi>(retrofit = get()) }
    single { TokenInterceptor() }

}

fun createOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(createHttpLoggingInterceptor(BuildConfig.DEBUG))
        .addInterceptor(tokenInterceptor)
        .build()
}

private fun createHttpLoggingInterceptor(debugMode: Boolean): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (debugMode) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    return httpLoggingInterceptor

}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}