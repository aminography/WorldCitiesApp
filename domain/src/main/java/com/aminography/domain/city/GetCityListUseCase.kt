package com.aminography.domain.city

import com.aminography.domain.base.BaseUseCase
import com.aminography.domain.di.IoDispatcher
import com.aminography.model.city.City
import com.aminography.model.common.Result
import com.aminography.scope.CityListScope
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author aminography
 */
@CityListScope
class GetCityListUseCase @Inject constructor(
    private val repository: CityRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, List<City>>(dispatcher) {

    private var cache: List<City>? = null

    override suspend fun execute(parameters: Unit): Result<List<City>> =
        (cache ?: repository.loadCityList().also { cache = it })
            .let { Result.Success(it) }
}