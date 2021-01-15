package com.aminography.data.datasource

import android.content.Context
import com.aminography.data.util.*
import com.aminography.model.City
import com.google.gson.Gson
import javax.inject.Inject

/**
 * @author aminography
 */
internal class CityDataSourceImpl @Inject constructor(
    private val context: Context,
    private val gson: Gson
) : CityDataSource {

    override suspend fun loadAllCities(): List<City> {
        val result = arrayListOf<City>()
        context.openAsset("cities.json")
            .toInputStreamReader()
            .toJsonReader()
            .use {
                it.beginArraySuspending()
                while (it.hasNextSuspending()) {
                    val city: City = gson.fromJson(it, City::class.java)
                    result.add(city)
                }
            }
        return result
    }
}