package com.exsilicium.common.utility

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceRetriever @Inject internal constructor(
        private val applicationContext: Context
) {

    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(applicationContext, colorRes)
    }

    fun getDimenPixels(@DimenRes dimenRes: Int): Int {
        return applicationContext.resources.getDimensionPixelSize(dimenRes)
    }

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        return if (formatArgs.isEmpty()) {
            applicationContext.getString(stringRes)
        } else {
            @Suppress("SpreadOperator")
            applicationContext.getString(stringRes, *formatArgs)
        }
    }
}