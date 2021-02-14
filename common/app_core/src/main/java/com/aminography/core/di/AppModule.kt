package com.aminography.core.di

import android.app.Application
import android.content.Context
import com.aminography.scope.annotation.AppScope
import dagger.Module
import dagger.Provides

/**
 * A dagger module class defining how to provide application related dependencies for injection.
 *
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