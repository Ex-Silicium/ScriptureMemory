package com.exsilicium.scripturememory.base

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            ActivityBindingModule::class,
            NetworkModule::class
        ]
)
interface ApplicationComponent {
    fun inject(myApplication: MyApplication)
}
