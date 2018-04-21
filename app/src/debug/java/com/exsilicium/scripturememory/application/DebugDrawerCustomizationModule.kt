package com.exsilicium.scripturememory.application

import com.exsilicium.common.ui.DebugDrawerCustomization
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
internal abstract class DebugDrawerCustomizationModule {

    @Binds
    @IntoSet
    abstract fun provideVersionCustomization(customization: VersionCustomization): DebugDrawerCustomization

    @Binds
    @IntoSet
    abstract fun provideBuildInfoCustomization(customization: BuildInfoCustomization): DebugDrawerCustomization
}
