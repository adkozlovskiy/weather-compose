package com.adkozlovskiy.weather_compose.data

import com.adkozlovskiy.weather_compose.data.api.WeatherServices
import com.adkozlovskiy.weather_compose.data.api.model.CurrentWeatherResponse
import com.adkozlovskiy.weather_compose.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherServices,
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherResponse {
        return remoteDataSource.getCurrentWeather(lat, lon)
    }
}