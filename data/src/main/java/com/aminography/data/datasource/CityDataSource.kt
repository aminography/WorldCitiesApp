package com.aminography.data.datasource

import com.aminography.model.City

/**
 * @author aminography
 */
interface CityDataSource {

    suspend fun loadAllCities(): List<City>
}