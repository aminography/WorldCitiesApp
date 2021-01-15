package com.aminography.domain.city

import com.aminography.domain.ds.RadixTree
import com.aminography.model.City

/**
 * @author aminography
 */
interface CityRepository {

    suspend fun loadCityList(): List<City>

    suspend fun loadCityRadixTree(): RadixTree<City>
}