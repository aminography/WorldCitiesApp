package com.aminography.data.city.datasource

import android.content.Context
import com.aminography.data.util.*
import com.aminography.domain.city.adapter.Inserter
import com.aminography.domain.city.adapter.MutableListAdapter
import com.aminography.domain.city.adapter.RadixTreeAdapter
import com.aminography.domain.ds.MinimalRadixTree
import com.aminography.domain.ds.RadixTree
import com.aminography.model.City
import com.google.gson.Gson
import javax.inject.Inject

/**
 * @author aminography
 */
internal class CityDataSourceImpl @Inject constructor(
    private val context: Context,
    private val gson: Gson,
    private val fileName: String
) : CityDataSource {

    override suspend fun loadCityList(): List<City> =
        arrayListOf<City>().also {
            readTo(MutableListAdapter(it))
        }

    override suspend fun loadCityRadixTree(): RadixTree<City> =
        MinimalRadixTree<City>().also {
            readTo(RadixTreeAdapter(it))
        }

    private suspend fun readTo(inserter: Inserter<City>) {
        context.openAsset(fileName)
            .toInputStreamReader()
            .toJsonReader()
            .use {
                it.beginArraySuspending()
                while (it.hasNextSuspending()) {
                    val city: City = gson.fromJson(it, City::class.java)
                    inserter.insert(city)
                }
            }
    }
}



