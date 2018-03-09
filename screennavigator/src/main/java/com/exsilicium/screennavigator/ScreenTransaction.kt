package com.exsilicium.screennavigator

import android.app.Activity
import android.os.Bundle
import kotlin.reflect.KClass

data class ScreenTransaction(
        val screen: Screen,
        val animationConfiguration: AnimationConfiguration = AnimationConfiguration.slideIn()
) {

    companion object {
        fun forActivity(activityClass: KClass<out Activity>, args: Bundle? = null): ScreenTransaction {
            return withActivityScreen(ActivityScreen(activityClass, args))
        }

        fun withActivityScreen(activityScreen: ActivityScreen) = ScreenTransaction(activityScreen)
    }
}