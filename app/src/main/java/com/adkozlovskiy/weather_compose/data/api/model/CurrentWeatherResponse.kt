package com.adkozlovskiy.weather_compose.data.api.model

import com.adkozlovskiy.weather_compose.data.mapper.CurrentWeatherMapper
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.util.TemperatureScale
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("weather")
    val weatherResponse: List<WeatherResponse>,
    @SerializedName("main")
    val mainResponse: MainResponse,
    @SerializedName("wind")
    val windResponse: WindResponse,
    @SerializedName("clouds")
    val cloudsResponse: CloudsResponse,
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("sys")
    val sysResponse: SysResponse,
) {
    fun toCurrentWeather(mapper: CurrentWeatherMapper, scale: TemperatureScale): CurrentWeather {
        return mapper.map(this, scale)
    }
}

data class WeatherResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
)

data class MainResponse(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
)

data class WindResponse(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double,
)

data class CloudsResponse(
    @SerializedName("all")
    val all: Int,
)

data class SysResponse(
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
)