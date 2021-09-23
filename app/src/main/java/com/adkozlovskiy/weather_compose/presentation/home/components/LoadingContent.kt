package com.adkozlovskiy.weather_compose.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.adkozlovskiy.weather_compose.presentation.theme.BackgroundDark
import com.adkozlovskiy.weather_compose.presentation.theme.BackgroundLight

@Composable
fun LoadingContent() {
    val gradient = Brush.verticalGradient(listOf(BackgroundDark, BackgroundLight))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Preview
@Composable
fun LoadingContentPreview() {
    LoadingContent()
}