package com.exsilicium.scripturememory.application

import com.exsilicium.common.dagger.ApplicationModule
import com.exsilicium.network.TestNetworkModule
import com.exsilicium.scripturememory.activity.TestActivityBindingModule
import com.exsilicium.scripturememory.passagedetail.PassageDetailControllerTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            TestActivityBindingModule::class,
            TestNetworkModule::class
        ]
)
internal interface TestApplicationComponent : FlavorComponent {

    fun inject(passageDetailControllerTest: PassageDetailControllerTest)
}