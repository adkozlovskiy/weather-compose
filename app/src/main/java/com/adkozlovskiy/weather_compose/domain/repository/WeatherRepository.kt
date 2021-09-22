package com.adkozlovskiy.weather_compose.domain.repository

import com.adkozlovskiy.weather_compose.data.api.model.CurrentWeatherResponse

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherResponse

}