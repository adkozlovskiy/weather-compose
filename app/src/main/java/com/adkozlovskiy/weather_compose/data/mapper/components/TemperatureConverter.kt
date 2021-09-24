package com.adkozlovskiy.weather_compose.data.mapper.components

import javax.inject.Inject
import kotlin.math.round

class TemperatureConverter @Inject constructor() {
    fun fromKelvins(kelvins: Double, scale: TemperatureScale): String {
        val roundedKelvins =
            when (scale) {
                TemperatureScale.KELVIN -> roundToKelvins(kelvins)
                TemperatureScale.CELSIUS -> roundToCelsius(kelvins)
                TemperatureScale.FAHRENHEIT -> roundToFahrenheit(kelvins)
            }

        return roundedKelvins.toString() + scale.value
    }

    private fun roundToCelsius(kelvins: Double): Int {
        return round(kelvins - 273.15).toInt()
    }

    private fun roundToKelvins(kelvins: Double): Int {
        return round(kelvins).toInt()
    }

    private fun roundToFahrenheit(kelvins: Double): Int {
        return round((kelvins - 273.15) * 9 / 5 + 32).toInt()
    }
}