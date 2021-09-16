package com.aminography.domain.city

import com.aminography.coroutine.di.IoDispatcher
import com.aminography.domain.base.BaseUseCase
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * The use-case of loading cities.
 *
 * @param dispatcher the [CoroutineDispatcher] that the job should be executed on.
 * @param cityRepository an instance of [CityRepository].
 *
 * @author aminography
 */
@FeatureScope
class LoadCitiesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val cityRepository: CityRepository
) : BaseUseCase<Unit, Unit>(dispatcher) {

    override suspend fun execute(param: Unit): Result<Unit> =
        runCatching {
            cityRepository.loadCities()
            Result.success(Unit)
        }
}
