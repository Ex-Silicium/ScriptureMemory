package com.exsilicium.scripturememory.helper

import android.content.Context
import android.widget.Toast

object UiUtils {
    fun notImplemented(context: Context, feature: String) {
        Toast.makeText(context, "Not yet implemented: $feature", Toast.LENGTH_LONG).show()
    }
}
