package com.aminography.data

import com.aminography.domain.city.CityRepository
import com.aminography.model.City
import com.aminography.model.Coordination
import javax.inject.Inject

/**
 * @author aminography
 */
class CityRepositoryImpl @Inject constructor(
) : CityRepository {

    override fun getAllCities(): List<City> {
        return listOf(
            City(1, "Amsterdam", "NL", Coordination(1.0, 1.0)),
            City(2, "Berlin", "GR", Coordination(2.0, 2.0)),
            City(3, "Zurich", "SW", Coordination(3.0, 3.0))
        )
    }
}