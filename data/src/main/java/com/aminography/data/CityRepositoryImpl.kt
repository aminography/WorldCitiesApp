package com.aminography.data

import com.aminography.data.datasource.CityDataSource
import com.aminography.domain.city.CityRepository
import com.aminography.model.City
import javax.inject.Inject

/**
 * @author aminography
 */
internal class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDataSource
) : CityRepository {

    override suspend fun loadAllCities(): List<City> =
        dataSource.loadAllCities()
}