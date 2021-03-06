package com.exsilicium.common.ui

import dagger.Module
import dagger.Provides

@Module
abstract class ActivityViewInterceptorModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun provideDefaultActivityViewInterceptor(): ActivityViewInterceptor {
            return ActivityViewInterceptor.DefaultActivityViewInterceptor()
        }
    }
}