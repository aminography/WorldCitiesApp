package com.aminography.data.core.gson

import com.aminography.scope.annotation.AppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author aminography
 */
@Module
class GsonModule {

    @AppScope
    @Provides
    internal fun providesGson(): Gson = GsonBuilder().create()

    @AppScope
    @Provides
    internal fun providesGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory = GsonConverterFactory.create(gson)
}
