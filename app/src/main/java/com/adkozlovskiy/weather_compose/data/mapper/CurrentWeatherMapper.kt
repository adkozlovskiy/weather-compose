package com.adkozlovskiy.weather_compose.data.mapper

import com.adkozlovskiy.weather_compose.data.api.model.CurrentWeatherResponse
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.util.IconResolver
import com.adkozlovskiy.weather_compose.util.TemperatureConverter
import com.adkozlovskiy.weather_compose.util.TemperatureScale
import com.adkozlovskiy.weather_compose.util.WindConverter
import javax.inject.Inject

interface CurrentWeatherMapper {
    fun map(weather: CurrentWeatherResponse, scale: TemperatureScale): CurrentWeather
}

class CurrentWeatherMapperImpl @Inject constructor(
    private val iconResolver: IconResolver,
    private val tempConverter: TemperatureConverter,
    private val windConverter: WindConverter,
) : CurrentWeatherMapper {

    override fun map(weather: CurrentWeatherResponse, scale: TemperatureScale) = CurrentWeather(
        title = weather.weatherResponse[0].main,
        temp = tempConverter.fromKelvins(weather.mainResponse.temp, scale),
        feelsLike = tempConverter.fromKelvins(weather.mainResponse.feelsLike, scale),
        iconId = iconResolver.resolve(weather.weatherResponse[0].icon),
        pressure = "${weather.mainResponse.pressure}hPa",
        humidity = "${weather.mainResponse.humidity}%",
        clouds = "${weather.cloudsResponse.all}%",
        wind = windConverter.toString(weather.windResponse.deg, weather.windResponse.speed)
    )
}