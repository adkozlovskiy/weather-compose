package com.adkozlovskiy.weather_compose.data.mapper.components

import com.adkozlovskiy.weather_compose.R
import javax.inject.Inject

class IconConverter @Inject constructor() {
    fun fromStringToResId(iconStr: String): Int = when (iconStr) {
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
        else -> throw IllegalArgumentException(" no resource for this icon $iconStr")
    }
}