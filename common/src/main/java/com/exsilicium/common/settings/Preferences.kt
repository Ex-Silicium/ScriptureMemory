package com.exsilicium.common.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Preferences @Inject internal constructor(
        context: Context
) {

    private val sharedPreferences = context.getSharedPreferences("settings", MODE_PRIVATE)

    var useNightMode: Boolean
        get() = sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)
        set(use) = sharedPreferences.edit { putBoolean(KEY_NIGHT_MODE, use) }

    companion object {
        private const val KEY_NIGHT_MODE = "night_mode"
    }
}