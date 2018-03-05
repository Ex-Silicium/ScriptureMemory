package com.exsilicium.common.ui

import com.exsilicium.screennavigator.ScreenNavigator
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class NavigationModule {
    @Binds
    internal abstract fun provideScreenNavigator(screenNavigator: DefaultScreenNavigator): ScreenNavigator

    @Binds
    @IntoSet
    internal abstract fun provideScreenNavigatorActivityLifecycleObserver(
            screenNavigator: DefaultScreenNavigator
    ): ActivityLifecycleObserver

    @Binds
    @IntoSet
    internal abstract fun provideGridHelperActivityLifecycleObserver(
            gridHelper: GridHelper
    ): ActivityLifecycleObserver
}