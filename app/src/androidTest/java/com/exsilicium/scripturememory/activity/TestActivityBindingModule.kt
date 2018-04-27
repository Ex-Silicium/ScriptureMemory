package com.exsilicium.scripturememory.activity

import android.app.Activity
import com.exsilicium.common.dagger.ActivityComponentLifecyclePlugin
import com.exsilicium.common.dagger.ActivityInjector
import com.exsilicium.passagedetail.PassageDetailActivity
import com.exsilicium.passagedetail.TestPassageDetailActivityComponent
import com.exsilicium.scripturememory.home.HomeActivity
import com.exsilicium.scripturememory.home.TestHomeActivityComponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(
        subcomponents = [
            TestHomeActivityComponent::class,
            TestPassageDetailActivityComponent::class
        ]
)
internal abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    abstract fun provideHomeActivityInjector(
            builder: TestHomeActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(PassageDetailActivity::class)
    abstract fun providePassageDetailActivityInjector(
            builder: TestPassageDetailActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideActivityInjector(
                activityInjectors: Map<Class<out Activity>,
                        @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>,
                lifecyclePlugin: ActivityComponentLifecyclePlugin
        ) = ActivityInjector(activityInjectors, lifecyclePlugin)
    }
}