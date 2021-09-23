package com.adkozlovskiy.weather_compose.util

import android.content.Context
import com.adkozlovskiy.weather_compose.R
import com.adkozlovskiy.weather_compose.util.TemperatureScale.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.round

interface IconResolver {
    fun resolve(icon: String): Int
}

class IconResolverImpl @Inject constructor() : IconResolver {
    override fun resolve(icon: String): Int {
        return when (icon) {
            "01d" -> R.drawable.d1
            "02d" -> R.drawable.d2
            "03d" -> R.drawable.d3
            "04d" -> R.drawable.d4
            "09d" -> R.drawable.d9
            "10d" -> R.drawable.d10
            "11d" -> R.drawable.d11
            "13d" -> R.drawable.d13
            "50d" -> R.drawable.d50

            "01n" -> R.drawable.n1
            "02n" -> R.drawable.n2
            "03n" -> R.drawable.n3
            "04n" -> R.drawable.n4
            "09n" -> R.drawable.n9
            "10n" -> R.drawable.n10
            "11n" -> R.drawable.n11
            "13n" -> R.drawable.n13
            "50n" -> R.drawable.n50
            else -> throw IllegalArgumentException(" incorrect icon")
        }
    }
}

class TemperatureConverter @Inject constructor() {
    fun fromKelvins(tempInKelvins: Double, scale: TemperatureScale): String = when (scale) {
        KELVIN -> "${round(tempInKelvins).toInt()}\u00B0K"
        CELSIUS -> "${round(tempInKelvins - 273.15).toInt()}\u2103"
        FAHRENHEIT -> "${round((tempInKelvins - 273.15) * 9 / 5 + 32).toInt()}\u2109"
    }
}

enum class TemperatureScale {
    KELVIN, CELSIUS, FAHRENHEIT
}

class WindConverter @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun toString(deg: Int, speed: Double): String {
        val degResId = when {
            deg >= 22.5 && deg < 67.5 -> R.string.wind_ne
            deg >= 67.5 && deg < 112.5 -> R.string.wind_e
            deg >= 112.5 && deg < 157.5 -> R.string.wind_se
            deg >= 157.5 && deg < 202.5 -> R.string.wind_s
            deg >= 202.5 && deg < 247.5 -> R.string.wind_sw
            deg >= 247.5 && deg < 292.5 -> R.string.wind_w
            deg >= 292.5 && deg < 337.5 -> R.string.wind_nw
            else -> R.string.wind_n
        }

        return "${speed}m/s, " + context.getString(degResId)
    }
}

