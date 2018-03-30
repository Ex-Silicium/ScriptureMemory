package com.exsilicium.common.ui

import dagger.Binds
import dagger.Module

@Module
abstract class ActivityViewInterceptorModule {

    @Binds
    abstract fun bindDebugActivityViewInterceptor(interceptor: DebugActivityViewInterceptor): ActivityViewInterceptor
}