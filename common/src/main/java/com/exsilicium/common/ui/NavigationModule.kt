package com.exsilicium.common.ui

import com.exsilicium.screennavigator.ScreenNavigator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {

    @Binds
    internal abstract fun provideScreenNavigator(screenNavigator: DefaultScreenNavigator): ScreenNavigator
}