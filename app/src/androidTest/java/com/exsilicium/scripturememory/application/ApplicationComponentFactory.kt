package com.exsilicium.scripturememory.application

import android.app.Application
import com.exsilicium.common.dagger.ApplicationModule

internal object ApplicationComponentFactory {
    fun create(application: Application): MainComponent {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(ApplicationModule(application))
                .build()
    }
}