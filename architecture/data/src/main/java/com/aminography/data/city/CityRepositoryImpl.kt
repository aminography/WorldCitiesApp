package com.aminography.data.city

import androidx.annotation.VisibleForTesting
import androidx.paging.PagingData
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.paging.PagingFactory
import com.aminography.domain.city.CityRepository
import com.aminography.domain.city.util.key
import com.aminography.model.city.City
import com.aminography.radixtree.RadixTree
import com.aminography.radixtree.mutableRadixTreeOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * The concrete implementation of the [CityRepository].
 *
 * @param dataSource an instance of [CityDataSource].
 * @param pagingFactory an instance of [PagingFactory] which is responsible to produce [PagingData].
 *
 * @author aminography
 */
internal class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDataSource,
    private val pagingFactory: PagingFactory<City>
) : CityRepository {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var cache: RadixTree<City>? = null

    private val mutex = Mutex()

    /*
     * The implementation of the [RadixTree] is in such a way that by inserting sorted keys, the
     * result of performing each prefix search remains sorted too. As the data set is partially
     * sorted, the time it takes to sort the list of entities is not long.
     */
    override suspend fun loadCities() {
        if (cache == null) {
            mutex.withLock {
                if (cache == null) {
                    cache = mutableRadixTreeOf<City>().apply {
                        val sorted = dataSource.loadCityListConcurrently()
                        for (city in sorted) put(city.key, city)
                    }
                }
            }
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