package com.exsilicium.scripturememory.application

import com.exsilicium.common.dagger.ApplicationModule
import com.exsilicium.network.TestNetworkModule
import com.exsilicium.persistence.database.TestPersistenceModule
import com.exsilicium.scripturememory.activity.TestActivityBindingModule
import com.exsilicium.scripturememory.home.MemoryPassageControllerTest
import com.exsilicium.scripturememory.passagedetail.PassageDetailControllerTest
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ApplicationModule::class,
            TestActivityBindingModule::class,
            TestNetworkModule::class,
            TestPersistenceModule::class
        ]
)
internal interface TestApplicationComponent : FlavorComponent {

    fun inject(memoryPassageControllerTest: MemoryPassageControllerTest)
    fun inject(passageDetailControllerTest: PassageDetailControllerTest)
}