package com.exsilicium.network

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideHttpCache(appContext: Context) = Cache(appContext.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    @Named(KEY_API_TOKEN)
    internal fun provideApiToken() = BuildConfig.ESV_API_KEY

    @Provides
    @Singleton
    internal fun provideOkhttpClient(
            cache: Cache,
            @Named(KEY_API_TOKEN) token: String
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                    { chain ->
                        chain.proceed(
                                chain.request().newBuilder().addHeader(
                                        "Authorization", "Token $token"
                                ).build()
                        )
                    }
            )
            .cache(cache)
            .build()

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.esv.org/v3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    internal companion object {
        private const val KEY_API_TOKEN = "KEY_API_TOKEN"
    }
}
