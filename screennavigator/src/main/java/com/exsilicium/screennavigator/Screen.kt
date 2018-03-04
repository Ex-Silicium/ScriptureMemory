package com.exsilicium.screennavigator

import android.app.Activity
import com.bluelinelabs.conductor.Router

interface Screen {
    fun launchScreen(
            activity: Activity,
            router: Router,
            animationConfiguration: AnimationConfiguration
    )
}