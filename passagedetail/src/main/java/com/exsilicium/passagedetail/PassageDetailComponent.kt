package com.exsilicium.passagedetail

import com.exsilicium.common.dagger.ScreenModule
import com.exsilicium.daggerannotations.ScreenScope
import com.exsilicium.passagedetail.service.PassageServiceModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ScreenScope
@Subcomponent(
        modules = [
            PassageServiceModule::class,
            ScreenModule::class
        ]
)
internal interface PassageDetailComponent : AndroidInjector<PassageDetailController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PassageDetailController>()
}
