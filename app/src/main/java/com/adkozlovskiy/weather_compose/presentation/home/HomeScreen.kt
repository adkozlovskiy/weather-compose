package com.adkozlovskiy.weather_compose.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adkozlovskiy.weather_compose.R
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.presentation.home.components.FailureContent
import com.adkozlovskiy.weather_compose.presentation.home.components.LoadingContent
import com.adkozlovskiy.weather_compose.presentation.home.components.WeatherContent

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel()
) {
    val weatherState = viewModel.weatherState.collectAsState()

    when (val state = weatherState.value) {
        is CurrentWeatherState.Success -> WeatherContent(currentWeather = state.data)
        is CurrentWeatherState.Failure -> FailureContent(failureInfo = state.failureInfo)
        is CurrentWeatherState.Loading -> LoadingContent()
    }
}

@Preview
@Composable
fun WeatherContentPreview() {
    WeatherContent(
        currentWeather = CurrentWeather(
            "Clouds",
            "23\u2103",
            "22\u2103",
            R.drawable.d1,
            "87%",
            "47%",
            "1.37m/s, S",
            "1019hPa",
            "Sermide"
        )
    )
}