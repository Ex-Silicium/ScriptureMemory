package com.exsilicium.common.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebugPreferences @Inject internal constructor(
        context: Context
) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("debug_settings", MODE_PRIVATE)

    var useMockPreferences: Boolean
        get() = sharedPreferences.getBoolean(KEY_MOCK_RESPONSES, false)
        set(use) = sharedPreferences.edit { putBoolean(KEY_MOCK_RESPONSES, use) }

    companion object {
        private const val KEY_MOCK_RESPONSES = "mock_response"
    }
}