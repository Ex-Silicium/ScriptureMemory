package com.exsilicium.common.dagger

import com.exsilicium.common.disposable.DefaultDisposableManager
import com.exsilicium.common.disposable.DisposableManager
import com.exsilicium.daggerannotations.ForScreen
import com.exsilicium.daggerannotations.ScreenScope
import dagger.Binds
import dagger.Module

@Module
abstract class ScreenModule {

    @Binds
    @ForScreen
    @ScreenScope
    internal abstract fun bindDisposableManager(disposableManager: DefaultDisposableManager): DisposableManager
}