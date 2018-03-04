package com.exsilicium.passagedetail

import com.bluelinelabs.conductor.Controller
import com.exsilicium.common.dagger.ControllerKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            PassageDetailComponent::class
        ]
)
internal abstract class PassageDetailScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(PassageDetailController::class)
    abstract fun bindPassageDetailController(
            builder: PassageDetailComponent.Builder
    ): AndroidInjector.Factory<out Controller>
}
