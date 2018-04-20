package com.exsilicium.common.utility

import android.content.Context
import android.widget.Toast
import androidx.core.widget.toast

object UiUtils {
    fun notImplemented(context: Context, feature: String) {
        context.toast("Not yet implemented: $feature", Toast.LENGTH_LONG)
    }
}
