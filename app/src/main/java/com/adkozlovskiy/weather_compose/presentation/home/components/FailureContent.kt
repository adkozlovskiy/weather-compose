package com.adkozlovskiy.weather_compose.presentation.home.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.adkozlovskiy.weather_compose.common.FailureInfo

@Composable
fun FailureContent(failureInfo: FailureInfo) {
    Text(text = failureInfo.toString())
}