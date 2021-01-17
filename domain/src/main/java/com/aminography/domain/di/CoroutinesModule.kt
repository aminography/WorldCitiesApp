package com.aminography.domain.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author aminography
 */
@Module
class CoroutinesModule {

    @Provides
    @DefaultDispatcher
    internal fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    internal fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    internal fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
