package com.adkozlovskiy.weather_compose.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

fun Context.getDrawableCompat(resId: Int): Drawable {
    return ResourcesCompat.getDrawable(resources, resId, theme) ?: throw IllegalStateException()
}