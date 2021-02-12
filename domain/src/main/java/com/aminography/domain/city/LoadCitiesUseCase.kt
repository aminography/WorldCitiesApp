package com.aminography.domain.city

import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.base.Result
import com.aminography.domain.di.IoDispatcher
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * The use-case of loading cities.
 *
 * @param cityRepository an instance of [CityRepository].
 * @param dispatcher the [CoroutineDispatcher] that the flow should produce values on.
 *
 * @author aminography
 */
@FeatureScope
class LoadCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<Unit, Result<Unit>>(dispatcher) {

    override fun execute(parameter: Unit): Flow<Result<Unit>> = flow {
        emit(Result.Loading)
        cityRepository.loadCities()
        emit(Result.Success(Unit))
    }
}