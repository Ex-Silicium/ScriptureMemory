package com.exsilicium.scripturememory.application

import com.exsilicium.common.base.BaseApplication
import com.exsilicium.common.dagger.ApplicationModule
import com.exsilicium.scripturememory.BuildConfig
import timber.log.Timber

internal class MyApplication : BaseApplication() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }
}