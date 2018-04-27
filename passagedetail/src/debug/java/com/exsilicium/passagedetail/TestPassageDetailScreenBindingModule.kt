package com.exsilicium.passagedetail

import com.bluelinelabs.conductor.Controller
import com.exsilicium.common.dagger.ControllerKey
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            TestPassageDetailComponent::class
        ]
)
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
internal abstract class TestPassageDetailScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(PassageDetailController::class)
    abstract fun bindPassageDetailController(
            builder: TestPassageDetailComponent.Builder
    ): AndroidInjector.Factory<out Controller>
}