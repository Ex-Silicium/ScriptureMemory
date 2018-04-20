package com.exsilicium.common.dagger

import com.exsilicium.common.disposable.DefaultDisposableManager
import com.exsilicium.common.disposable.DisposableManager
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
    abstract fun bindToolbarActivityLifecycleObserver(toolbarService: ToolbarService): ActivityLifecycleObserver

    @Binds
    @ForActivity
    @ActivityScope
    internal abstract fun bindDisposableManager(disposableManager: DefaultDisposableManager): DisposableManager
}