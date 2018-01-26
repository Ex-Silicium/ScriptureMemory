package com.exsilicium.scripturememory.memorypassage

import com.exsilicium.scripturememory.di.ActivityScope
import com.exsilicium.scripturememory.ui.NavigationModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = [
            MainScreenBindingModule::class,
            NavigationModule::class
        ]
)
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}