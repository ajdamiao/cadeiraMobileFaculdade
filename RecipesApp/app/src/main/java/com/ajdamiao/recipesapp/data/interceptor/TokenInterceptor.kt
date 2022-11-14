package com.ajdamiao.recipesapp.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        val token = "9c0f3a60e968401ab6f736e17ccaceec"
        val url = original.url().newBuilder().addQueryParameter("apiKey", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}