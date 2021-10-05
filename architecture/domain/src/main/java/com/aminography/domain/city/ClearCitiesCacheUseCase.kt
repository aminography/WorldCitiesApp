package com.aminography.domain.city

import com.aminography.coroutine.di.MainDispatcher
import com.aminography.domain.base.BaseUseCase
import com.aminography.domain.base.UseCase
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * The use-case of clearing cache of cities in the [CityRepository].
 *
 * @param dispatcher the [CoroutineDispatcher] that the job should be executed on.
 * @param cityRepository an instance of [CityRepository].
 *
 * @author aminography
 */
@FeatureScope
class ClearCitiesCacheUseCase @Inject constructor(
    @MainDispatcher dispatcher: CoroutineDispatcher,
    private val cityRepository: CityRepository
) : BaseUseCase<UseCase.NoParam, Unit>(dispatcher) {

    override suspend fun execute(param: UseCase.NoParam): Result<Unit> =
        runCatching {
            cityRepository.clearCache()
            Result.success(Unit)
        }
}
