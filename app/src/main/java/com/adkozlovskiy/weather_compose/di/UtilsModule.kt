package com.adkozlovskiy.weather_compose.di

import com.adkozlovskiy.weather_compose.data.mapper.CurrentWeatherMapper
import com.adkozlovskiy.weather_compose.data.mapper.CurrentWeatherMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {

    @Binds
    fun bindCurrentWeatherMapper(impl: CurrentWeatherMapperImpl): CurrentWeatherMapper

}