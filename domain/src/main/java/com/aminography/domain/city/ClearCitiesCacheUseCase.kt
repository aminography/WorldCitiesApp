package com.aminography.domain.city

import com.aminography.domain.base.BaseUseCase
import com.aminography.domain.base.Result
import com.aminography.scope.feature.CityListScope
import javax.inject.Inject

/**
 * The use-case of clearing cache of cities in the [CityRepository].
 *
 * @param cityRepository an instance of [CityRepository].
 *
 * @author aminography
 */
@CityListScope
class ClearCitiesCacheUseCase @Inject constructor(
    private val cityRepository: CityRepository
) : BaseUseCase<Unit, Unit>() {

    override fun execute(parameter: Unit): Result<Unit> {
        cityRepository.clearCache()
        return Result.Success(Unit)
    }
}