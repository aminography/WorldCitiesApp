package com.aminography.domain.city

import com.aminography.model.City

/**
 * @author aminography
 */
interface CityRepository {

    suspend fun loadAllCities(): List<City>
}