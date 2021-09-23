package com.adkozlovskiy.weather_compose.common

sealed class Resource<out R> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure(val failureInfo: FailureInfo) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    companion object
}

sealed class FailureInfo(open val e: Exception) {
    class HttpException(override val e: Exception) : FailureInfo(e)
    class IOException(override val e: Exception) : FailureInfo(e)
    class Unresolved(override val e: Exception) : FailureInfo(e)
}