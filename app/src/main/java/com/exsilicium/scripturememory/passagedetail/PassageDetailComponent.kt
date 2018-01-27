package com.exsilicium.scripturememory.passagedetail

import com.exsilicium.scripturememory.di.ScreenScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            PassageServiceModule::class
        ]
)
interface PassageDetailComponent : AndroidInjector<PassageDetailController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailController>()
}
