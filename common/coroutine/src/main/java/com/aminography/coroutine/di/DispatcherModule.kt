package com.aminography.coroutine.di

import com.aminography.scope.annotation.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A dagger module class defining how to provide [CoroutineDispatcher]s for injection.
 *
 * @author aminography
 */
@Module
class DispatcherModule {

    @AppScope
    @Provides
    @DefaultDispatcher
    internal fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @AppScope
    @Provides
    @IoDispatcher
    internal fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @AppScope
    @Provides
    @MainDispatcher
    internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
