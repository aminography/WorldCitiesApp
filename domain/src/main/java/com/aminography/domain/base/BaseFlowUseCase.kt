package com.aminography.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author aminography
 */
abstract class BaseFlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: P): Flow<R> =
        execute(parameters).flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<R>
}
