package com.exsilicium.common.ui

import dagger.Binds
import dagger.Module

@Module
abstract class ActivityViewInterceptorModule {

    @Binds
    internal abstract fun bindDebugActivityViewInterceptor(
            interceptor: DebugActivityViewInterceptor
    ): ActivityViewInterceptor
}