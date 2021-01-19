package com.aminography.data.city

import androidx.paging.PagingData
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.paging.PagingFactory
import com.aminography.domain.city.CityRepository
import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import com.aminography.domain.city.util.key
import com.aminography.model.city.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * @author aminography
 */
internal class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDataSource,
    private val pagingFactory: PagingFactory<City>
) : CityRepository {

    private var cache: RadixTree<City>? = null

    override suspend fun loadCities(): RadixTree<City> {
        return if (cache != null) cache!!
        else MinimalRadixTree<City>().also { tree ->
            val list = dataSource.loadCityList()
            val sorted = list.sortedBy { it.name }
            for (city in sorted) {
                tree.insert(city.key, city)
            }
            cache = tree
        }
    }

    override fun searchCities(query: String): Flow<PagingData<City>> {
        return if (cache == null) flowOf(PagingData.empty())
        else pagingFactory.createPagingDataFlow(cache!!, query)
    }

    override fun clearCache() {
        cache = null
    }
}