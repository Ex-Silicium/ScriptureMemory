package com.exsilicium.scripturememory.passagedetail

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal class PassageServiceModule {
    @Provides
    @Singleton
    internal fun providePassageService(retrofit: Retrofit) = retrofit.create(PassageService::class.java)
}
