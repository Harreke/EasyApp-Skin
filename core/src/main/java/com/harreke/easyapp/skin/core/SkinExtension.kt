package com.harreke.easyapp.skin.core

import android.content.Context
import android.content.res.TypedArray
import androidx.core.content.ContextCompat

internal fun Context?.sharedPreference(name: String) = this?.getSharedPreferences(name, Context.MODE_PRIVATE)

internal fun TypedArray.getCompatResourceId(index: Int, compatIndex: Int, defaultValue: Int): Int {
    var resourceId = getResourceId(index, defaultValue)
    if (resourceId == defaultValue) {
        resourceId = getResourceId(compatIndex, defaultValue)
    }
    return resourceId
}