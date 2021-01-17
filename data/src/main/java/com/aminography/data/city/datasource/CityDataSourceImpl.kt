package com.aminography.data.city.datasource

import android.content.Context
import com.aminography.data.util.openAsset
import com.aminography.data.util.toInputStreamReader
import com.aminography.data.util.toJsonReader
import com.aminography.domain.city.adapter.Inserter
import com.aminography.domain.city.adapter.MutableListAdapter
import com.aminography.domain.city.adapter.RadixTreeAdapter
import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City
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

    private fun readTo(inserter: Inserter<City>) {
        context.openAsset(fileName)
            .toInputStreamReader()
            .toJsonReader()
            .use {
                it.beginArray()
                while (it.hasNext()) {
                    val city: City = gson.fromJson(it, City::class.java)
                    inserter.insert(city)
                }
            }
    }
}



