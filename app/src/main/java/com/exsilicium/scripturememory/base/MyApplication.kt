package com.exsilicium.scripturememory.base

import android.app.Application
import com.exsilicium.scripturememory.BuildConfig
import com.exsilicium.scripturememory.di.ActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MyApplication : Application() {

    @Inject lateinit var activityInjector: ActivityInjector

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