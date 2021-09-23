package com.adkozlovskiy.weather_compose.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adkozlovskiy.weather_compose.R
import com.adkozlovskiy.weather_compose.common.FailureInfo
import com.adkozlovskiy.weather_compose.domain.model.CurrentWeather

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel()
) {

    val weatherState = viewModel.weatherState.collectAsState()

    when (val value = weatherState.value) {
        is CurrentWeatherState.Success -> WeatherContent(currentWeather = value.data)
        is CurrentWeatherState.Failure -> FailureContent(failureInfo = value.failureInfo)
        is CurrentWeatherState.Loading -> LoadingContent()
    }
}

@Composable
fun FailureContent(failureInfo: FailureInfo) {
    Text(text = failureInfo.toString())
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun WeatherContent(currentWeather: CurrentWeather) {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.header_night),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.4f)) {
                Column {
                    LocationToolbar(locationTitle = "Москва")
                    Spacer(modifier = Modifier.height(48.dp))
                    GeneralWeather(temp = currentWeather.temp, iconId = currentWeather.iconId)
                }
            }
            DetailedWeather(weather = currentWeather)
        }
    }
}

@Composable
fun DetailedWeather(weather: CurrentWeather) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        elevation = 8.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {


    }
}

@Composable
fun LocationToolbar(locationTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 56.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = locationTitle,
            color = Color.White,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(end = 6.dp)
        )
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = "change location"
        )
    }
}

@Composable
fun GeneralWeather(temp: String, @DrawableRes iconId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = temp,
                color = Color.White,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(end = 12.dp)
            )
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = iconId),
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Солнечно", style = MaterialTheme.typography.body1, color = Color.White)
        Text(
            text = "По ощущению: 13℃",
            style = MaterialTheme.typography.body1,
            color = Color.White,
        )
    }
}

@Preview
@Composable
fun WeatherContentPreview() {
    WeatherContent(
        currentWeather = CurrentWeather(
            "Ясно",
            "23\u2103",
            "24\u2103",
            R.drawable.d1,
            "100%",
            "100%",
            "СЗ",
            "10000"
        )
    )
}

@Preview
@Composable
fun LoadingContentPreview() {
    LoadingContent()
}