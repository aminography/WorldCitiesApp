package com.aminography.domain.di

import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * The qualifier annotation class corresponding to [Dispatchers.Default].
 *
 * @author aminography
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

/**
 * The qualifier annotation class corresponding to [Dispatchers.IO].
 *
 * @author aminography
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

/**
 * The qualifier annotation class corresponding to [Dispatchers.Main].
 *
 * @author aminography
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher
