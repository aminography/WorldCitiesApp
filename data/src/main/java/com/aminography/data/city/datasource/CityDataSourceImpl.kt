package com.aminography.data.city.datasource

import com.aminography.domain.city.adapter.MutableListAdapter
import com.aminography.domain.city.adapter.RadixTreeAdapter
import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City
import javax.inject.Inject

/**
 * @author aminography
 */
internal class CityDataSourceImpl @Inject constructor(
    private val jsonRetriever: JsonRetriever,
    private val fileName: String
) : CityDataSource {

    override suspend fun loadCityList(): List<City> =
        arrayListOf<City>().also {
            jsonRetriever.readTo(fileName, MutableListAdapter(it))
        }

    override suspend fun loadCityRadixTree(): RadixTree<City> =
        MinimalRadixTree<City>().also {
            jsonRetriever.readTo(fileName, RadixTreeAdapter(it))
        }
}



