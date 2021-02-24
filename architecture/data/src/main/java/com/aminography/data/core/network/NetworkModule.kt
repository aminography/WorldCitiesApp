package com.aminography.data.core.network

import com.aminography.data.KEY_BASE_URL
import com.aminography.data.core.persistent.pref.settings.SettingsDataSource
import com.aminography.scope.annotation.FoundationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * @author aminography
 */
@Module
class NetworkModule {

    @FoundationScope
    @Provides
    @Named(KEY_BASE_URL)
    internal fun providesBaseUrl(
        settingsDataSource: SettingsDataSource
    ): String = settingsDataSource.baseUrl

    @FoundationScope
    @Provides
    internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @FoundationScope
    @Provides
    internal fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @FoundationScope
    @Provides
    internal fun providesRetrofit(
        @Named(KEY_BASE_URL) baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}
