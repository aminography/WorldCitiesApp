package com.aminography.domain.city

import androidx.paging.PagingData
import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City
import kotlinx.coroutines.flow.Flow

/**
 * @author aminography
 */
interface CityRepository {

    suspend fun loadCities(): RadixTree<City>

    fun searchCities(query: String): Flow<PagingData<City>>

    fun clearCache()
}