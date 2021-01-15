package com.aminography.domain.base

import com.aminography.model.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * @author aminography
 */
abstract class BaseUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Result<R> =
        try {
            withContext(dispatcher) { execute(parameters) }
        } catch (e: Exception) {
            Result.Error(e)
        }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): Result<R>
}
