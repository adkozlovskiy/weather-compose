package com.adkozlovskiy.weather_compose.di

import com.adkozlovskiy.weather_compose.data.WeatherRepositoryImpl
import com.adkozlovskiy.weather_compose.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: WeatherRepositoryImpl): WeatherRepository

}