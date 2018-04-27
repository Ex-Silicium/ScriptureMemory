package com.exsilicium.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
class TestNetworkModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
}