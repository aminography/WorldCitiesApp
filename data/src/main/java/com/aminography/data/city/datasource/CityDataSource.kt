package com.aminography.data.city.datasource

import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City

/**
 * The abstraction of the city data source that defines expected functions for the concrete child
 * class.
 *
 * @author aminography
 */
internal interface CityDataSource {

    /**
     * Loads cities as a [List] of cities.
     *
     * @return the [List] of the cities loaded from file.
     */
    suspend fun loadCityList(): List<City>

    /**
     * Loads cities as a [List] of cities using a concurrent approach.
     *
     * @return the [List] of the cities loaded from file.
     */
    suspend fun loadCityListConcurrently(): List<City>

    /**
     * Loads cities as a [RadixTree] of cities.
     *
     * @return the [RadixTree] of the cities loaded from file.
     */
    suspend fun loadCityRadixTree(): RadixTree<City>
}