package com.adkozlovskiy.weather_compose.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel()
) {

    val weatherState = viewModel.weatherState.collectAsState()

    Box {
        when (val value = weatherState.value) {
            is CurrentWeatherState.Success -> {
                Log.d("TAG", "HomeScreen: s")
                Text(text = "${value.data}")
            }
            is CurrentWeatherState.Failure -> {
                Text(text = "error")
            }
            is CurrentWeatherState.Loading -> {
                Log.d("TAG", "HomeScreen: l")

            }
        }
    }
}

@Composable
fun WeatherContent(
    viewModel: MainViewModel,
) {

}

@Composable
fun Progress(
    viewModel: MainViewModel,
) {
    CircularProgressIndicator()
}

@Composable
fun FailureMessage(
    viewModel: MainViewModel,
) {

}