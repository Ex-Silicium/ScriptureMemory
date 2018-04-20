package com.exsilicium.scripturememory.application

import android.view.View
import android.widget.TextView
import com.exsilicium.common.ui.DebugDrawerCustomization
import com.exsilicium.scripturememory.BuildConfig
import com.exsilicium.scripturememory.R
import javax.inject.Inject

class VersionCustomization @Inject constructor(
) : DebugDrawerCustomization {

    override fun customize(debugLayout: View?) {
        debugLayout!!.findViewById<TextView>(R.id.tv_version_name).text = BuildConfig.VERSION_NAME
        debugLayout.findViewById<TextView>(R.id.tv_version_code).text = BuildConfig.VERSION_CODE.toString()
    }
}