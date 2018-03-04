package com.exsilicium.scripturememory.home

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerextension.annotation.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(modules = [
    MemoryPassageScreenModule::class,
    ScreenModule::class
])
internal interface MemoryPassageComponent : AndroidInjector<MemoryPassageController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MemoryPassageController>()
}
