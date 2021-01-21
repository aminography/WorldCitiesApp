package com.aminography.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * The base class to define the behavior of [Flow] provider use-cases.
 *
 * @param P the type of the input parameter.
 * @param R the type of the generic type foe the returned [Flow].
 * @param dispatcher the [CoroutineDispatcher] that the resulting flow should produce values on.
 *
 * @author aminography
 */
abstract class BaseFlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    /**
     * Produces a [Flow] by executing [execute] method on the [dispatcher].
     *
     * @param parameter the parameter needed by the use-case.
     *
     * @return the [Flow] returned by [execute] that is forced to produce values on the [dispatcher].
     */
    operator fun invoke(parameter: P): Flow<R> =
        execute(parameter).flowOn(dispatcher)

    /**
     * The function that should be implemented in concrete child classes, which defines the
     * behaviour of the use-case.
     *
     * @param parameter the parameter needed by the use-case.
     *
     * @return the [Flow] that emits values based on the behaviour of the use-case.
     */
    protected abstract fun execute(parameter: P): Flow<R>
}
