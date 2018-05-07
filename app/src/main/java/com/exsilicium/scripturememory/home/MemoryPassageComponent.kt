package com.exsilicium.scripturememory.home

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerannotations.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            ScreenModule::class
        ]
)
internal interface MemoryPassageComponent : AndroidInjector<MemoryPassageController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MemoryPassageController>()
}
