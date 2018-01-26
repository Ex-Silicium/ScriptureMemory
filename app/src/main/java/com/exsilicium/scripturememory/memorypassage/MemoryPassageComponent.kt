package com.exsilicium.scripturememory.memorypassage

import com.exsilicium.scripturememory.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent
interface MemoryPassageComponent : AndroidInjector<MemoryPassageController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MemoryPassageController>()
}