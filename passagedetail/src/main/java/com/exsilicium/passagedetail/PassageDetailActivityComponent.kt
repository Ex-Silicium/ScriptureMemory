package com.exsilicium.passagedetail

import com.exsilicium.common.dagger.ActivityComponent
import com.exsilicium.common.dagger.ActivityModule
import com.exsilicium.common.ui.NavigationModule
import com.exsilicium.daggerextension.annotation.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(
        modules = [
            ActivityModule::class,
            NavigationModule::class,
            PassageDetailScreenBindingModule::class
        ]
)
interface PassageDetailActivityComponent : ActivityComponent<PassageDetailActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailActivity>()
}