package com.aminography.domain.city

import androidx.paging.PagingData
import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.di.DefaultDispatcher
import com.aminography.model.city.City
import com.aminography.scope.CityListScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

/**
 * @author aminography
 */
@CityListScope
class SearchCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<String, PagingData<City>>(dispatcher) {

    override fun execute(parameters: String): Flow<PagingData<City>> =
        parameters.toLowerCase(Locale.getDefault()).let { query ->
            cityRepository.searchCities(query)
        }
}