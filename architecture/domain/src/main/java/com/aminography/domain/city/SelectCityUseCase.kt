package com.aminography.domain.city

import com.aminography.domain.base.BaseUseCase
import com.aminography.domain.base.Result
import com.aminography.model.city.City
import com.aminography.scope.annotation.FeatureScope
import javax.inject.Inject

/**
 * The use-case of selecting a city.
 *
 * @author aminography
 */
@FeatureScope
class SelectCityUseCase @Inject constructor() : BaseUseCase<City, City>() {

    override fun execute(parameter: City): Result<City> {
        return Result.Success(parameter)
    }
}