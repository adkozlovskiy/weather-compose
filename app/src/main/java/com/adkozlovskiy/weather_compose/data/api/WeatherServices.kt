package com.adkozlovskiy.weather_compose.data.api

import com.adkozlovskiy.weather_compose.data.api.model.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double, @Query("lon") lon: Double
    ): CurrentWeatherResponse
}