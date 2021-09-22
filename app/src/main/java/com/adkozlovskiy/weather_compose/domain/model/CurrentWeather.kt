package com.adkozlovskiy.weather_compose.domain.model

import android.graphics.drawable.Drawable

data class CurrentWeather(
    val title: String,
    val temp: String,
    val feelsLike: String,
    val icon: Drawable,
    val clouds: String,
    val humidity: String,
    val wind: String,
    val pressure: String,
)