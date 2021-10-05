package com.aminography.domain.city

import com.aminography.coroutine.di.MainDispatcher
import com.aminography.domain.base.BaseUseCase
import com.aminography.domain.base.UseCase
import com.aminography.model.city.City
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * The use-case of selecting a city.
 *
 * @param dispatcher the [CoroutineDispatcher] that the job should be executed on.
 *
 * @author aminography
 */
@FeatureScope
class SelectCityUseCase @Inject constructor(
    @MainDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<SelectCityUseCase.Param, City>(dispatcher) {

    override suspend fun execute(param: Param): Result<City> =
        param.runCatching { city }

    class Param(
        val city: City
    ) : UseCase.Param
}
