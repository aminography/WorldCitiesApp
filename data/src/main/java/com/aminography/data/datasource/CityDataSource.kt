package com.aminography.data.datasource

import com.aminography.domain.ds.RadixTree
import com.aminography.model.City

/**
 * @author aminography
 */
interface CityDataSource {

    suspend fun loadCityList(): List<City>

    suspend fun loadCityRadixTree(): RadixTree<City>
}