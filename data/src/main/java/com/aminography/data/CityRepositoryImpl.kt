package com.aminography.data

import com.aminography.data.datasource.CityDataSource
import com.aminography.domain.city.CityRepository
import com.aminography.domain.ds.RadixTree
import com.aminography.model.City
import javax.inject.Inject

/**
 * @author aminography
 */
internal class CityRepositoryImpl @Inject constructor(
    private val dataSource: CityDataSource
) : CityRepository {

    override suspend fun loadCityList(): List<City> =
        dataSource.loadCityList()

    override suspend fun loadCityRadixTree(): RadixTree<City> =
        dataSource.loadCityRadixTree()
}