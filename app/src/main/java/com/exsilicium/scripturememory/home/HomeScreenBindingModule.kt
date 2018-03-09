package com.exsilicium.scripturememory.home

import com.bluelinelabs.conductor.Controller
import com.exsilicium.common.dagger.ControllerKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            MemoryPassageComponent::class
        ]
)
internal abstract class HomeScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(MemoryPassageController::class)
    abstract fun bindMemoryPassageController(
            builder: MemoryPassageComponent.Builder
    ): AndroidInjector.Factory<out Controller>
}
