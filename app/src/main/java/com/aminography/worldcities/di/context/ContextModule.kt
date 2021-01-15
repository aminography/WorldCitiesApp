package com.aminography.worldcities.di.context

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class ContextModule {

    @Provides
    fun provideContext(
        application: Application
    ): Context = application
}