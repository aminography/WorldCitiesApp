package com.aminography.domain.city

import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.di.IoDispatcher
import com.aminography.domain.ds.RadixTree
import com.aminography.model.City
import com.aminography.model.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author aminography
 */
class GetCityTreeFlowUseCase @Inject constructor(
    private val repository: CityRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<Unit, RadixTree<City>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<RadixTree<City>>> = flow {
        emit(Result.Loading)
        emit(Result.Success(repository.loadCityRadixTree()))
    }
}