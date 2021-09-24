package com.adkozlovskiy.weather_compose.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.adkozlovskiy.weather_compose.common.FailureInfo
import com.adkozlovskiy.weather_compose.domain.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GeolocationManager @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    @ApplicationContext private val context: Context,
) {

    fun askForLocation(): Flow<LocationRequest> = flow {
        emit(LocationRequest.Loading)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            emit(LocationRequest.NoPermission)
        } else try {
            val location = fusedLocationProviderClient.awaitLastLocation()

            if (location == null) {
                emit(LocationRequest.NullLocation)

            } else {
                emit(
                    LocationRequest.Success(
                        Location(location.latitude, location.longitude)
                    )
                )
            }
        } catch (ex: Exception) {
            emit(LocationRequest.Failure(FailureInfo.Unresolved))
        }
    }
}

sealed class LocationRequest {
    object Loading : LocationRequest()
    object NoPermission : LocationRequest()
    object NullLocation : LocationRequest()

    class Success(val location: Location) : LocationRequest()
    class Failure(val failureInfo: FailureInfo) : LocationRequest()
}