package com.aminography.data.datasource

import com.aminography.model.City

/**
 * @author aminography
 */
interface CityDataSource {

    fun getAllCities(): List<City>
}