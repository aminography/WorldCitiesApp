package com.aminography.domain.city

import com.aminography.model.City

/**
 * @author aminography
 */
interface CityRepository {

    fun getAllCities(): List<City>
}