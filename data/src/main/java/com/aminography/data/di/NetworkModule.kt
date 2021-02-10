package com.aminography.data.di

import com.aminography.data.local.pref.settings.SettingsDataSource
import com.aminography.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author aminography
 */
@Module
class NetworkModule {

    @Provides
    internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    internal fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @AppScope
    @Provides
    internal fun providesRetrofit(
        okHttpClient: OkHttpClient,
        settingsDataSource: SettingsDataSource
    ): Retrofit = Retrofit.Builder()
        .baseUrl(settingsDataSource.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}
