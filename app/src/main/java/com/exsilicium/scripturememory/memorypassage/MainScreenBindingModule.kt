package com.exsilicium.scripturememory.memorypassage

import com.bluelinelabs.conductor.Controller
import com.exsilicium.scripturememory.di.ControllerKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            MemoryPassageComponent::class
        ]
)
abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(MemoryPassageController::class)
    abstract fun bindHomeController(builder: MemoryPassageComponent.Builder): AndroidInjector.Factory<out Controller>
}