package com.adkozlovskiy.weather_compose.presentation.home.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.adkozlovskiy.weather_compose.R
import com.adkozlovskiy.weather_compose.common.FailureInfo

@Composable
fun FailureContent(
    failureInfo: FailureInfo,
    onRetry: () -> Unit
) {
    val (title, desc) = when (failureInfo) {
        is FailureInfo.Unresolved -> Pair(
            stringResource(id = R.string.unresolved_error),
            stringResource(id = R.string.unresolved_error_desc)
        )
        is FailureInfo.IOException -> Pair(
            stringResource(id = R.string.internet_error),
            stringResource(id = R.string.internet_error_desc)
        )
        is FailureInfo.HttpException -> Pair(
            stringResource(id = R.string.server_error),
            stringResource(id = R.string.server_error_desc)
        )
        is FailureInfo.LocationError -> Pair(
            stringResource(id = R.string.geolocation_error),
            stringResource(id = R.string.geolocation_error_desc)
        )
    }
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = title)
        },
        text = {
            Text(text = desc)
        },
        confirmButton = {
            TextButton(onClick = { onRetry() }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    )
}