package com.aminography.domain.base

import com.aminography.model.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * @author aminography
 */
abstract class BaseFlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<Result<R>> =
        execute(parameters)
            .catch { emit(Result.Error(it)) }
            .flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<Result<R>>
}
