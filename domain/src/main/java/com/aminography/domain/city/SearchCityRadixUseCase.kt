package com.aminography.domain.city

import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.di.DefaultDispatcher
import com.aminography.model.city.City
import com.aminography.model.common.Result
import com.aminography.model.common.map
import com.aminography.scope.CityListScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * @author aminography
 */
@CityListScope
class SearchCityRadixUseCase @Inject constructor(
    private val getCityTreeUseCase: GetCityTreeUseCase,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<String, List<City>>(dispatcher) {

    override fun execute(parameters: String): Flow<Result<List<City>>> = flow {
        emit(Result.Loading)
        val query = parameters.toLowerCase(Locale.getDefault())
        val result = getCityTreeUseCase(Unit)

        val filtered = result.map { tree ->
            tree.searchPrefix(query)
                .sortedBy { it.name }
        }
        emit(filtered)
    }
}