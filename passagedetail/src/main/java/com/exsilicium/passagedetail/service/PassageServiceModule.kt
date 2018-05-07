package com.exsilicium.passagedetail.service

import com.exsilicium.daggerannotations.ScreenScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class PassageServiceModule {

    @Provides
    @ScreenScope
    fun providePassageService(retrofit: Retrofit): PassageService = retrofit.create(PassageService::class.java)
}
