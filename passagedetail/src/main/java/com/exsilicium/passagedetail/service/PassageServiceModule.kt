package com.exsilicium.passagedetail.service

import com.exsilicium.daggerextension.annotation.ScreenScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal object PassageServiceModule {
    @Provides
    @ScreenScope
    @JvmStatic
    fun providePassageService(retrofit: Retrofit) = retrofit.create(PassageService::class.java)!!
}
