package com.exsilicium.common.nightmode

import com.exsilicium.common.settings.Preferences
import com.exsilicium.common.ui.ActivityLifecycleObserver
import com.exsilicium.daggerannotations.ActivityScope
import javax.inject.Inject

@ActivityScope
internal class NightModeManager @Inject constructor(
        private val preferences: Preferences
) : ActivityLifecycleObserver() {

    fun setUseNightMode(useNightMode: Boolean) {
        preferences.useNightMode = useNightMode
        activity?.recreate()
    }
}