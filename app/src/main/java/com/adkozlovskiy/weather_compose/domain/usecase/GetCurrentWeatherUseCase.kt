package com.adkozlovskiy.weather_compose.domain.usecase

import com.adkozlovskiy.weather_compose.common.FailureInfo
import com.adkozlovskiy.weather_compose.common.Resource
import com.adkozlovskiy.weather_compose.data.mapper.CurrentWeatherMapper
import com.adkozlovskiy.weather_compose.data.mapper.components.PressureScale
import com.adkozlovskiy.weather_compose.data.mapper.components.Scales
import com.adkozlovskiy.weather_compose.data.mapper.components.TemperatureScale
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.domain.model.Location
import com.adkozlovskiy.weather_compose.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val mapper: CurrentWeatherMapper,
) {

    operator fun invoke(location: Location): Flow<Resource<CurrentWeather>> = flow {
        try {
            emit(Resource.Loading)
            val currentWeather =
                weatherRepository.getCurrentWeather(location.latitude, location.longitude)
                    .toCurrentWeather(mapper, Scales(TemperatureScale.CELSIUS, PressureScale.HPA))
            emit(Resource.Success(currentWeather))
        } catch (e: HttpException) {
            emit(Resource.Failure(FailureInfo.HttpException))

        } catch (e: IOException) {
            emit(Resource.Failure(FailureInfo.IOException))

        } catch (e: Exception) {
            emit(Resource.Failure(FailureInfo.Unresolved))
        }
    }
}