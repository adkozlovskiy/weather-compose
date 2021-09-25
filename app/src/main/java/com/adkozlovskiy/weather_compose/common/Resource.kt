package com.adkozlovskiy.weather_compose.common

sealed class Resource<out R> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val failureInfo: FailureInfo) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    companion object
}

sealed class FailureInfo {
    object HttpException : FailureInfo()
    object IOException : FailureInfo()
    object Unresolved : FailureInfo()
    object LocationError : FailureInfo()
}