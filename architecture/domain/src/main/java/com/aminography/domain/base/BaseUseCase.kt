package com.aminography.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * The base class to define the behavior of use-cases.
 *
 * @param P the type of the input parameter.
 * @param R the type of the output wrapped by [Result].
 * @param dispatcher the [CoroutineDispatcher] that execution should be done on.
 */
abstract class BaseUseCase<in P, out R>(
    private val dispatcher: CoroutineDispatcher
) : UseCase<P, R> where P : UseCase.Param, R : Any {

    /**
     * Produces a [Result] by executing the [execute] method.
     *
     * @param param the parameter needed by the use-case.
     *
     * @return the [Result] of the execution.
     */
    suspend operator fun invoke(param: P): Result<R> =
        withContext(dispatcher) {
            try {
                execute(param)
            } catch (t: Throwable) {
                Result.failure(t)
            }
        }

    /**
     * This function should be implemented in concrete child classes, which defines the behavior of
     * the use-case.
     *
     * @param param the parameter needed by the use-case.
     *
     * @return the [Result] of the execution.
     *
     * @throws Throwable if any exceptions occurs during the execution of method.
     */
    @Throws(Throwable::class)
    protected abstract suspend fun execute(param: P): Result<R>
}
