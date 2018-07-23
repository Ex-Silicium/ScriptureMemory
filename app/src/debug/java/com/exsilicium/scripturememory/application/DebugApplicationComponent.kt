package com.exsilicium.scripturememory.application

import com.exsilicium.common.dagger.ApplicationModule
import com.exsilicium.network.NetworkModule
import com.exsilicium.persistence.PersistenceModule
import com.exsilicium.scripturememory.activity.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ApplicationModule::class,
            ActivityBindingModule::class,
            NetworkModule::class,
            PersistenceModule::class,
            DebugDrawerCustomizationModule::class
        ]
)
internal interface DebugApplicationComponent : FlavorComponent
