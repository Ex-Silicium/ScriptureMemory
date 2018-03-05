package com.exsilicium.scripturememory.activity

import android.app.Activity
import com.exsilicium.common.dagger.ActivityComponentLifecyclePlugin
import com.exsilicium.common.dagger.ActivityInjector
import com.exsilicium.passagedetail.PassageDetailActivity
import com.exsilicium.passagedetail.PassageDetailActivityComponent
import com.exsilicium.passagepicker.PassagePickerActivity
import com.exsilicium.passagepicker.PassagePickerActivityComponent
import com.exsilicium.scripturememory.home.HomeActivity
import com.exsilicium.scripturememory.home.HomeActivityComponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module(
        subcomponents = [
            HomeActivityComponent::class,
            PassageDetailActivityComponent::class,
            PassagePickerActivityComponent::class
        ]
)
internal abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    abstract fun provideHomeActivityInjector(
            builder: HomeActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(PassageDetailActivity::class)
    abstract fun providePassageDetailActivityInjector(
            builder: PassageDetailActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(PassagePickerActivity::class)
    abstract fun providePassagePickerActivityInjector(
            builder: PassagePickerActivityComponent.Builder
    ): AndroidInjector.Factory<out Activity>

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideActivityInjector(
                activityInjectors: Map<Class<out Activity>,
                        @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>,
                lifecyclePlugin: ActivityComponentLifecyclePlugin
        ) = ActivityInjector(activityInjectors, lifecyclePlugin)
    }
}
