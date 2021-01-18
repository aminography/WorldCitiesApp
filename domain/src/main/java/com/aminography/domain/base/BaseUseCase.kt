package com.aminography.domain.base

/**
 * @author aminography
 */
abstract class BaseUseCase<in P, R> {

    operator fun invoke(parameters: P): Result<R> =
        try {
            execute(parameters)
        } catch (e: Exception) {
            Result.Error(e)
        }

    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): Result<R>
}