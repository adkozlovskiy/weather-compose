package com.adkozlovskiy.weather_compose.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.adkozlovskiy.weather_compose.R
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather
import com.adkozlovskiy.weather_compose.presentation.theme.BackgroundDark
import com.adkozlovskiy.weather_compose.presentation.theme.BackgroundLight
import com.adkozlovskiy.weather_compose.presentation.theme.OnCardTextPrimary
import com.adkozlovskiy.weather_compose.presentation.theme.OnCardTextSecondary

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherContent(currentWeather: CurrentWeather) {
    val gradient = Brush.verticalGradient(listOf(BackgroundDark, BackgroundLight))
    Box(
        modifier = Modifier.background(gradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.4f)) {
                Column {
                    LocationToolbar(location = currentWeather.location)
                    Spacer(modifier = Modifier.height(48.dp))
                    WeatherHeader(data = currentWeather)
                }
            }

            val animVisibleState = remember { MutableTransitionState(false) }
                .apply { targetState = true }

            AnimatedVisibility(
                visibleState = animVisibleState,
                enter = slideInVertically(initialOffsetY = { it })
            ) {
                DetailedWeather(weather = currentWeather)
            }
        }
    }
}

@Composable
fun LocationToolbar(location: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 56.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(end = 6.dp),
            text = location,
            color = Color.White,
            style = MaterialTheme.typography.body1
        )
        Image(
            modifier = Modifier
                .size(18.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = ""
        )
    }
}

@Composable
fun WeatherHeader(data: CurrentWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.temp,
                color = Color.White,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(end = 12.dp)
            )
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = data.iconId),
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = data.title,
            style = MaterialTheme.typography.body1,
            color = Color.White
        )
        Text(
            text = "Feels like: ${data.feelsLike}",
            style = MaterialTheme.typography.body1,
            color = Color.White,
        )
    }
}


@Composable
fun SunriseSunsetContent(sunrise: Long, sunset: Long, current: Long) {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 80.dp), onDraw = {
        drawArc(
            OnCardTextSecondary,
            -9.5f,
            -160f,
            false,
            size = Size(size.width, 360f),
            style = Stroke(width = 2.dp.toPx())
        )
    })
}

@Composable
fun WeatherDetails(weather: CurrentWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            WeatherDetailsItem(title = "Облачность", description = weather.clouds)
            WeatherDetailsItem(title = "Влажность", description = weather.humidity)
        }

        Column {
            WeatherDetailsItem(title = "Давление", description = weather.pressure)
            WeatherDetailsItem(title = "Ветер", description = weather.wind)
        }
    }
}


@Composable
fun DetailedWeather(weather: CurrentWeather) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        elevation = 0.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 36.dp, horizontal = 36.dp)
        ) {
            SunriseSunsetContent(1, 1, 1)
            WeatherDetails(weather)
        }
    }
}


@Composable
fun WeatherDetailsItem(title: String, description: String) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body2,
            color = OnCardTextSecondary,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.h6,
            color = OnCardTextPrimary,
        )
    }
}