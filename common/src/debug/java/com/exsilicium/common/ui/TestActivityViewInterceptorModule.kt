package com.exsilicium.common.ui

import com.exsilicium.common.ui.ActivityViewInterceptor.DefaultActivityViewInterceptor
import dagger.Binds
import dagger.Module

@Module
abstract class TestActivityViewInterceptorModule {

    @Binds
    internal abstract fun bindDebugActivityViewInterceptor(
            interceptor: DefaultActivityViewInterceptor
    ): ActivityViewInterceptor
}