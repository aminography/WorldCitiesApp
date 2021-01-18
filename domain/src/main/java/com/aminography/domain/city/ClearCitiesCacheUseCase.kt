package com.aminography.domain.city

import com.aminography.domain.base.BaseUseCase
import com.aminography.domain.base.Result
import com.aminography.scope.CityListScope
import javax.inject.Inject

/**
 * @author aminography
 */
@CityListScope
class ClearCitiesCacheUseCase @Inject constructor(
    private val cityRepository: CityRepository
) : BaseUseCase<Unit, Unit>() {

    override fun execute(parameters: Unit): Result<Unit> {
        cityRepository.clearCache()
        return Result.Success(Unit)
    }
}