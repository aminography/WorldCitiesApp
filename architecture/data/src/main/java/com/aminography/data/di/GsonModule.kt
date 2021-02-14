package com.aminography.data.di

import com.aminography.scope.annotation.FoundationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class GsonModule {

    @FoundationScope
    @Provides
    internal fun providesGson(): Gson = GsonBuilder().create()
}
