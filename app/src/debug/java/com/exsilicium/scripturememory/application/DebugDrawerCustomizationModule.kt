package com.exsilicium.scripturememory.application

import com.exsilicium.common.ui.DebugDrawerCustomization
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class DebugDrawerCustomizationModule {

    @Binds
    @IntoSet
    abstract fun provideVersionCustomization(customization: VersionCustomization): DebugDrawerCustomization
}