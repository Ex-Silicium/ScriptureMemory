package com.exsilicium.common.ui

import android.app.Activity
import android.support.annotation.LayoutRes

interface ActivityViewInterceptor {

    fun setContentView(activity: Activity, @LayoutRes layoutRes: Int)

    fun clear()

    @Suppress("unused")
    class DefaultActivityViewInterceptor : ActivityViewInterceptor {

        override fun setContentView(activity: Activity, layoutRes: Int) {
            activity.setContentView(layoutRes)
        }

        override fun clear() {
        }
    }
}