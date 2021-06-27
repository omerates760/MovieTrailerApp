package com.monofire.movietrailerapp.data.network.service

import com.monofire.movietrailerapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:15          │
//└─────────────────────────────┘
class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url =
            originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
        val requestBuilder = original.newBuilder()
            .url(url)
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}