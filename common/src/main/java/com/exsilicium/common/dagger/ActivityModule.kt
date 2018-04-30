package com.exsilicium.common.dagger

import com.exsilicium.common.disposable.DefaultDisposableManager
import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.common.nightmode.NightModeManager
import com.exsilicium.common.toolbar.ToolbarService
import com.exsilicium.common.ui.ActivityLifecycleObserver
import com.exsilicium.common.ui.ActivityViewInterceptorModule
import com.exsilicium.daggerannotations.ActivityScope
import com.exsilicium.daggerannotations.ForActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module(
        includes = [
            ActivityViewInterceptorModule::class
        ]
)
abstract class ActivityModule {

    @Binds
    @IntoSet
    internal abstract fun bindToolbarActivityLifecycleObserver(
            toolbarService: ToolbarService
    ): ActivityLifecycleObserver

    @Binds
    @IntoSet
    internal abstract fun provideNightModeActivityLifecycleObserver(
            nightModeManager: NightModeManager
    ): ActivityLifecycleObserver

    @Binds
    @ForActivity
    @ActivityScope
    internal abstract fun bindDisposableManager(disposableManager: DefaultDisposableManager): DisposableManager
}