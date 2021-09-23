package com.adkozlovskiy.weather_compose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adkozlovskiy.weather_compose.common.FailureInfo
import com.adkozlovskiy.weather_compose.common.Resource
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.domain.model.Location
import com.adkozlovskiy.weather_compose.domain.usecase.GetCurrentWeatherUseCase
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
) : ViewModel() {

    private val _weatherState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
    val weatherState: StateFlow<CurrentWeatherState> = _weatherState

    init {
        loadCurrentWeatherInfo(Location(45.0, 11.3))
    }

    private fun loadCurrentWeatherInfo(location: Location) = viewModelScope.launch(Dispatchers.IO) {
        getCurrentWeatherUseCase(location).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    _weatherState.value = CurrentWeatherState.Success(resource.data)
                }
                is Resource.Failure -> {
                    _weatherState.value = CurrentWeatherState.Failure(resource.failureInfo)
                }
                is Resource.Loading -> {
                    _weatherState.value = CurrentWeatherState.Loading
                }
            }
        }
    }
}

sealed class CurrentWeatherState {
    object Loading : CurrentWeatherState()
    class Success(val data: CurrentWeather) : CurrentWeatherState()
    class Failure(val failureInfo: FailureInfo) : CurrentWeatherState()
}