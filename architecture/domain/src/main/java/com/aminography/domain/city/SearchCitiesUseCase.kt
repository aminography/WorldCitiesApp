package com.aminography.domain.city

import androidx.paging.PagingData
import com.aminography.coroutine.di.DefaultDispatcher
import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.model.city.City
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.util.Locale
import javax.inject.Inject

/**
 * The use-case of searching cities.
 *
 * @param cityRepository an instance of [CityRepository].
 * @param dispatcher the [CoroutineDispatcher] that the flow should produce values on.
 *
 * @author aminography
 */
@FeatureScope
class SearchCitiesUseCase @Inject constructor(
    @DefaultDispatcher dispatcher: CoroutineDispatcher,
    private val cityRepository: CityRepository
) : BaseFlowUseCase<String, PagingData<City>>(dispatcher) {

    override fun execute(param: String): Flow<PagingData<City>> =
        param.lowercase(Locale.getDefault()).let { query ->
            cityRepository.searchCities(query)
        }
}
