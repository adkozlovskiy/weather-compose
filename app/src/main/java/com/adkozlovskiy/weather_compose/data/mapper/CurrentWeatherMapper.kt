package com.adkozlovskiy.weather_compose.data.mapper

import com.adkozlovskiy.weather_compose.data.api.model.CurrentWeatherResponse
import com.adkozlovskiy.weather_compose.data.mapper.components.*
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface CurrentWeatherMapper {
    fun map(weather: CurrentWeatherResponse, scales: Scales): CurrentWeather
}

class CurrentWeatherMapperImpl @Inject constructor(
    private val iconResolver: IconConverter,
    private val tempConverter: TemperatureConverter,
    private val windConverter: WindConverter,
    private val timeFormat: SimpleDateFormat,
) : CurrentWeatherMapper {

    override fun map(weather: CurrentWeatherResponse, scales: Scales) = CurrentWeather(
        description = getTitledDescription(weather.weatherResponse[0].description),
        temp = getConvertedTemperature(weather.mainResponse.temp, scales.tempScale),
        feelsLike = getConvertedTemperature(weather.mainResponse.feelsLike, scales.tempScale),
        iconId = getIconResourceId(weather.weatherResponse[0].icon),
        pressure = getConvertedPressure(weather.mainResponse.pressure, scales.pressure),
        humidity = getHumidityPercent(weather.mainResponse.humidity),
        clouds = getCloudsPercent(weather.cloudsResponse.all),
        wind = getConverterWind(weather.windResponse.deg, weather.windResponse.speed),
        location = weather.name,
        sunrise = getTimeFormatted(weather.sysResponse.sunrise),
        sunset = getTimeFormatted(weather.sysResponse.sunset)
    )

    private fun getTitledDescription(desc: String): String {
        return desc.replaceFirstChar { it.uppercaseChar() }
    }

    private fun getConvertedTemperature(kelvins: Double, scale: TemperatureScale): String {
        return tempConverter.fromKelvins(kelvins, scale)
    }

    private fun getIconResourceId(iconStr: String): Int {
        return iconResolver.fromStringToResId(iconStr)
    }

    private fun getConvertedPressure(pressure: Int, scale: PressureScale): String {
        return "$pressure ${scale.value}"
    }

    private fun getHumidityPercent(humidity: Int): String {
        return "$humidity%"
    }

    private fun getCloudsPercent(clouds: Int): String {
        return "$clouds%"
    }

    private fun getConverterWind(deg: Int, speed: Double): String {
        return windConverter.toWindString(deg, speed)
    }

    private fun getTimeFormatted(millis: Long): String {
        val date = Date(millis * 1000)
        return timeFormat.format(date)
    }
}