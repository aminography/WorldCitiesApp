package com.aminography.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Base class to define the behavior of [Flow] provider use-cases.
 *
 * @param P the type of the input parameter.
 * @param R the type of the generic type foe the returned [Flow].
 * @param dispatcher the [CoroutineDispatcher] that the resulting flow should produce values on.
 */
abstract class BaseFlowUseCase<in P, out R>(
    private val dispatcher: CoroutineDispatcher
) : UseCase<P, R> where P : UseCase.Param, R : Any {

    /**
     * Produces a [Flow] by executing [execute] method on the [dispatcher].
     *
     * @param param the parameter needed by the use-case.
     *
     * @return the [Flow] returned by [execute] that is forced to produce values on the [dispatcher].
     */
    operator fun invoke(param: P): Flow<R> =
        execute(param).flowOn(dispatcher)

    /**
     * Function that should be implemented in concrete child classes, which defines the behaviour
     * of the use-case.
     *
     * @param param the parameter needed by the use-case.
     *
     * @return the [Flow] that emits values based on the behaviour of the use-case.
     */
    protected abstract fun execute(param: P): Flow<R>
}
