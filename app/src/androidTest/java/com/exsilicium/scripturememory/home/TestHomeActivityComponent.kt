package com.exsilicium.scripturememory.home

import com.exsilicium.common.dagger.TestActivityModule
import com.exsilicium.common.ui.NavigationModule
import com.exsilicium.daggerannotations.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = [
            TestActivityModule::class,
            NavigationModule::class,
            TestHomeScreenBindingModule::class
        ]
)
internal interface TestHomeActivityComponent : AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}