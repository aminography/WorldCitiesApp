package com.aminography.domain.city

import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.di.DefaultDispatcher
import com.aminography.model.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author aminography
 */
class GetCitiesFlowUseCase @Inject constructor(
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<Unit, List<String>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<String>>> = flow {
        emit(Result.Loading)
        emit(Result.Success(listOf("Amsterdam", "Berlin", "Zurich")))
    }
}