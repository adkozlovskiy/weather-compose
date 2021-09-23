package com.adkozlovskiy.weather_compose.domain.model

import androidx.annotation.DrawableRes

data class CurrentWeather(
    val title: String,
    val temp: String,
    val feelsLike: String,
    @DrawableRes
    val iconId: Int,
    val clouds: String,
    val humidity: String,
    val wind: String,
    val pressure: String,
    val location: String,
)