package com.adkozlovskiy.weather_compose.data.mapper.components

data class Scales(
    val tempScale: TemperatureScale,
    val pressure: PressureScale
)

enum class TemperatureScale(val value: String) {
    KELVIN("°K"), CELSIUS("℃"), FAHRENHEIT("℉")
}

enum class PressureScale(val value: String) {
    HPA("hPa")
}