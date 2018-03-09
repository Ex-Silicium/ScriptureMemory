package com.exsilicium.scripturememory.home

import com.exsilicium.common.dagger.ActivityComponent
import com.exsilicium.common.dagger.ActivityModule
import com.exsilicium.common.ui.NavigationModule
import com.exsilicium.daggerannotations.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = [
            ActivityModule::class,
            NavigationModule::class,
            HomeScreenBindingModule::class
        ]
)
internal interface HomeActivityComponent : ActivityComponent<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()
}
