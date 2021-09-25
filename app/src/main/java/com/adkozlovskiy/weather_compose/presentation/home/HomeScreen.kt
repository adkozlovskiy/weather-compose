package com.adkozlovskiy.weather_compose.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adkozlovskiy.weather_compose.R
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.presentation.home.components.FailureContent
import com.adkozlovskiy.weather_compose.presentation.home.components.LoadingContent
import com.adkozlovskiy.weather_compose.presentation.home.components.RequiresPermissions
import com.adkozlovskiy.weather_compose.presentation.home.components.WeatherContent
import com.adkozlovskiy.weather_compose.presentation.theme.BackgroundDark
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel()
) {
    val weatherState = viewModel.weatherState.collectAsState()
    when (val state = weatherState.value) {
        is WeatherState.Loading -> LoadingContent()
        is WeatherState.NoLocationPermission -> RequiresPermissions(
            onPermissionsGranted = {
                viewModel.getCurrentWeatherInfo()
            }
        )
        is WeatherState.Success -> WeatherContent(currentWeather = state.data)
        is WeatherState.Failure -> FailureContent(failureInfo = state.failureInfo,
            onRetry = {
                viewModel.getCurrentWeatherInfo()
            }
        )
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
            "Sermide", "23:00", "25:00"
        )
    )
}