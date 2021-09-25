package com.adkozlovskiy.weather_compose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adkozlovskiy.weather_compose.common.FailureInfo
import com.adkozlovskiy.weather_compose.common.Resource
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.domain.model.Location
import com.adkozlovskiy.weather_compose.domain.usecase.GetCurrentWeatherUseCase
import com.adkozlovskiy.weather_compose.util.GeolocationManager
import com.adkozlovskiy.weather_compose.util.LocationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val geolocationManager: GeolocationManager,
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    init {
        getCurrentWeatherInfo()
    }

    fun getCurrentWeatherInfo() = viewModelScope.launch(Dispatchers.IO) {
        geolocationManager.askForLocation().collect {
            when (it) {
                is LocationRequest.Loading -> {
                    _weatherState.value = WeatherState.Loading
                }
                is LocationRequest.Success -> loadCurrentWeatherInfo(it.location)
                is LocationRequest.NoPermission -> {
                    _weatherState.value = WeatherState.NoLocationPermission
                }
                is LocationRequest.NullLocation -> {
                    _weatherState.value = WeatherState.Failure(FailureInfo.LocationError)
                }
                is LocationRequest.Failure -> {
                    _weatherState.value = WeatherState.Failure(it.failureInfo)
                }
            }
        }
    }

    private suspend fun loadCurrentWeatherInfo(location: Location) {
        getCurrentWeatherUseCase(location).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    _weatherState.value = WeatherState.Success(resource.data)
                }
                is Resource.Failure -> {
                    _weatherState.value = WeatherState.Failure(resource.failureInfo)
                }
                is Resource.Loading -> {
                    _weatherState.value = WeatherState.Loading
                }
            }
        }
    }
}

sealed class WeatherState {
    object Loading : WeatherState()
    object NoLocationPermission : WeatherState()

    class Success(val data: CurrentWeather) : WeatherState()
    class Failure(val failureInfo: FailureInfo) : WeatherState()
}