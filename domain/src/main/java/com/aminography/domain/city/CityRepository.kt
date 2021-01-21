package com.aminography.domain.city

import androidx.paging.PagingData
import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City
import kotlinx.coroutines.flow.Flow

/**
 * The abstraction of the city repository that defines expected functions for the concrete child
 * class.
 *
 * @author aminography
 */
interface CityRepository {

    /**
     * Loads cities as an instance of [RadixTree].
     *
     * @return the loaded cities stored in a [RadixTree].
     */
    suspend fun loadCities(): RadixTree<City>

    /**
     * Performs a prefix search on cities based on the input [query].
     *
     * @param query the string to use as the prefix for search.
     *
     * @return a [Flow] of [PagingData] that enables user interface to load result of the search by
     * pagination.
     */
    fun searchCities(query: String): Flow<PagingData<City>>

    /**
     * Clears the cache of loaded cities.
     */
    fun clearCache()
}