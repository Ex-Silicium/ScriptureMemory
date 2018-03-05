package com.exsilicium.common.dagger

import com.exsilicium.common.disposable.DefaultDisposableManager
import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.common.screen.ScreenLifecycleTask
import com.exsilicium.daggerextension.annotation.ForScreen
import com.exsilicium.daggerextension.annotation.ScreenScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class ScreenModule {
    @Binds
    @ForScreen
    @ScreenScope
    internal abstract fun bindDisposableManager(disposableManager: DefaultDisposableManager): DisposableManager

    @Multibinds
    internal abstract fun screenLifecycleTasks(): Set<ScreenLifecycleTask>
}