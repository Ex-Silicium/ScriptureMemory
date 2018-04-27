package com.exsilicium.passagedetail

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.passagedetail.service.TestPassageServiceModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            TestPassageServiceModule::class,
            ScreenModule::class
        ]
)
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
internal interface TestPassageDetailComponent : AndroidInjector<PassageDetailController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailController>()
}