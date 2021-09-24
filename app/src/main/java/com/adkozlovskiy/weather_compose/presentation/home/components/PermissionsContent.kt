package com.adkozlovskiy.weather_compose.presentation.home.components

import android.Manifest
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.adkozlovskiy.weather_compose.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequiresPermissions(
    onPermissionsGranted: () -> Unit
) {
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    val permissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    PermissionsRequired(
        multiplePermissionsState = permissionsState,
        permissionsNotGrantedContent = {
            if (!doNotShowRationale) {
                Rationale(permissionsState = permissionsState) { doNotShowRationale = true }
            }
        },
        permissionsNotAvailableContent = {
            PermissionDenied()
        }
    ) {
        onPermissionsGranted()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Rationale(
    permissionsState: MultiplePermissionsState,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(id = R.string.rationale_title))
        },
        text = {
            Text(text = stringResource(id = R.string.rationale_text))
        },
        confirmButton = {
            TextButton(onClick = { permissionsState.launchMultiplePermissionRequest() }) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        }
    )
}

@Composable
fun PermissionDenied() {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = stringResource(id = R.string.perm_denied_title))
        },
        text = {
            Text(text = stringResource(id = R.string.perm_denied_text))
        },
        confirmButton = {
            TextButton(onClick = { }) {
                Text(text = stringResource(id = android.R.string.ok))
            }
        }
    )
}