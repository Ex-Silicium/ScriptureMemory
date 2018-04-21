package com.exsilicium.scripturememory.application

import com.exsilicium.common.dagger.ApplicationModule
import com.exsilicium.network.NetworkModule
import com.exsilicium.scripturememory.activity.ActivityBindingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            ActivityBindingModule::class,
            NetworkModule::class
        ]
)
internal interface ApplicationComponent : FlavorComponent
