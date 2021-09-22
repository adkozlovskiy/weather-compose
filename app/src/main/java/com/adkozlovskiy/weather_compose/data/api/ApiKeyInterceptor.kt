package com.adkozlovskiy.weather_compose.data.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()

        val urlWithApiKey = originalUrl.newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val API_KEY_QUERY = "appid"
        const val API_KEY = "ccd11b37c3e195a9d65eef947fccef27"
    }
}