package com.exsilicium.common.utility

import android.content.Context
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceRetriever @Inject constructor(
        private val applicationContext: Context
) {
    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        return if (formatArgs.isEmpty()) {
            applicationContext.getString(stringRes)
        } else {
            applicationContext.getString(stringRes, *formatArgs)
        }
    }

    fun getDimenPixels(@DimenRes dimenRes: Int): Int {
        return applicationContext.resources.getDimensionPixelSize(dimenRes)
    }
}