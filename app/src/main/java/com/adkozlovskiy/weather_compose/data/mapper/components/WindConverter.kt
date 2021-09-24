package com.adkozlovskiy.weather_compose.data.mapper.components

import android.content.Context
import com.adkozlovskiy.weather_compose.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WindConverter @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun toWindString(deg: Int, speed: Double): String {
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