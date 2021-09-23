package com.adkozlovskiy.weather_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adkozlovskiy.weather_compose.presentation.home.HomeScreen
import com.adkozlovskiy.weather_compose.presentation.theme.WeathercomposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeathercomposeTheme {
                HomeScreen()
            }
        }
    }
}