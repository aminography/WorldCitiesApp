package com.aminography.worldcities.di

import android.app.Application
import android.content.Context
import com.aminography.scope.AppScope
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class AppModule {

    @AppScope
    @Provides
    fun providesApplication(
        context: Context
    ): Application = (context as Application)
}