package com.exsilicium.passagedetail

import com.exsilicium.daggerextension.annotation.ScreenScope
import com.exsilicium.passagedetail.service.PassageServiceModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            PassageServiceModule::class
        ]
)
internal interface PassageDetailComponent : AndroidInjector<PassageDetailController> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailController>()
}
