package com.exsilicium.scripturememory.base

import android.app.Activity
import com.exsilicium.scripturememory.di.ActivityKey
import com.exsilicium.scripturememory.home.MainActivity
import com.exsilicium.scripturememory.home.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            MainActivityComponent::class
        ]
)
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun provideMainActivityInjector(
            builder: MainActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>
}
