package com.exsilicium.screennavigator

import android.app.Activity
import android.os.Bundle

data class ScreenTransaction(
        val screen: Screen,
        val animationConfiguration: AnimationConfiguration = AnimationConfiguration.slideIn()
) {
    companion object {
        fun forActivity(activityClass: Class<out Activity>): ScreenTransaction {
            return ScreenTransaction(ActivityScreen(activityClass))
        }

        fun forActivity(activityClass: Class<out Activity>, args: Bundle): ScreenTransaction {
            return ScreenTransaction(ActivityScreen(activityClass, args))
        }

        fun withActivityScreen(activityScreen: ActivityScreen): ScreenTransaction {
            return ScreenTransaction(activityScreen)
        }
    }
}