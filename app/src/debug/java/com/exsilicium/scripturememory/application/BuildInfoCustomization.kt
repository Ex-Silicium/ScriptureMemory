package com.exsilicium.scripturememory.application

import android.view.View
import android.widget.TextView
import com.exsilicium.common.ui.DebugDrawerCustomization
import com.exsilicium.scripturememory.BuildConfig
import com.exsilicium.scripturememory.R
import javax.inject.Inject

internal class BuildInfoCustomization @Inject constructor(
) : DebugDrawerCustomization {

    override fun customize(debugLayout: View) {
        debugLayout.findViewById<TextView>(R.id.tv_build_time).text = BuildConfig.BUILD_TIME
        debugLayout.findViewById<TextView>(R.id.tv_git_sha).text = BuildConfig.GIT_SHA
    }
}
