package com.aminography.domain.city

import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City

/**
 * @author aminography
 */
interface CityRepository {

    suspend fun loadCityList(): List<City>

    suspend fun loadCityRadixTree(): RadixTree<City>
}