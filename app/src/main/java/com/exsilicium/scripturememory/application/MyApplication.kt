package com.exsilicium.scripturememory.application

import android.support.annotation.VisibleForTesting
import com.exsilicium.common.base.BaseApplication
import com.exsilicium.scripturememory.BuildConfig
import timber.log.Timber

internal class MyApplication : BaseApplication() {

    @VisibleForTesting lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        component = ApplicationComponentFactory.create(this).apply {
            inject(this@MyApplication)
        }
    }
}
