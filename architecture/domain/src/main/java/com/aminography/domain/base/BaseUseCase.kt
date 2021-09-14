package com.aminography.domain.base

import kotlinx.coroutines.flow.Flow

/**
 * The base class to define the behavior of [Flow] provider use-cases.
 *
 * @param P the type of the input parameter.
 * @param R the type of the generic type foe the returned [Result].
 *
 * @author aminography
 */
abstract class BaseUseCase<in P, R> {

    /**
     * Produces a [Result] by executing [execute] method.
     *
     * @param parameter the parameter needed by the use-case.
     *
     * @return the [Result] that of the execution.
     */
    operator fun invoke(parameter: P): Result<R> =
        try {
            execute(parameter)
        } catch (e: Exception) {
            Result.Error(e)
        }

    /**
     * The function that should be implemented in concrete child classes, which defines the
     * behaviour of the use-case.
     *
     * @param parameter the parameter needed by the use-case.
     *
     * @return the [Result] that of the execution.
     *
     * @throws RuntimeException if any exceptions occur during the execution of method.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(parameter: P): Result<R>
}
