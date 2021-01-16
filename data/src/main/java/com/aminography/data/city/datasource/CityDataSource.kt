package com.aminography.data.city.datasource

import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City

/**
 * @author aminography
 */
interface CityDataSource {

    suspend fun loadCityList(): List<City>

    suspend fun loadCityRadixTree(): RadixTree<City>
}