package com.aminography.data.city.datasource

import com.aminography.data.city.datasource.adapter.MutableListAdapter
import com.aminography.data.city.datasource.adapter.RadixTreeAdapter
import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import com.aminography.domain.city.util.key
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
        arrayListOf<City>().apply {
            jsonRetriever.readTo(fileName, MutableListAdapter(this))
        }

    override suspend fun loadCityRadixTree(): RadixTree<City> =
        MinimalRadixTree<City>().apply {
            jsonRetriever.readTo(fileName, RadixTreeAdapter(this) { it.key })
        }
}



