package com.exsilicium.passagepicker.book

import com.exsilicium.common.ui.DebugDrawerCustomization
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class BookListDebugDrawerCustomizationModule {

    @Binds
    @IntoSet
    abstract fun provideVersionCustomization(customization: BookListCustomization): DebugDrawerCustomization
}