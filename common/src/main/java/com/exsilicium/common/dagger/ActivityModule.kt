package com.exsilicium.common.dagger

import com.exsilicium.common.disposable.DefaultDisposableManager
import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.daggerextension.annotation.ActivityScope
import com.exsilicium.daggerextension.annotation.ForActivity
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityModule {
    @Binds
    @ForActivity
    @ActivityScope
    internal abstract fun bindDisposableManager(disposableManager: DefaultDisposableManager): DisposableManager
}