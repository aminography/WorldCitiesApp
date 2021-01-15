package com.aminography.data.datasource

import com.aminography.model.City
import com.aminography.model.Coordination
import com.google.gson.Gson
import javax.inject.Inject

/**
 * @author aminography
 */
class CityDataSourceImpl @Inject constructor(
    private val gson: Gson
) : CityDataSource {

    override fun getAllCities(): List<City> {
        return listOf(
            City(1, "Amsterdam", "NL", Coordination(1.0, 1.0)),
            City(2, "Berlin", "GR", Coordination(2.0, 2.0)),
            City(3, "Zurich", "SW", Coordination(3.0, 3.0))
        )
    }
}