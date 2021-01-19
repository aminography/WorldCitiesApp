package com.aminography.data.city.datasource

import android.content.Context
import com.aminography.data.city.datasource.adapter.Inserter
import com.aminography.data.util.openAsset
import com.aminography.data.util.toInputStreamReader
import com.aminography.data.util.toJsonReader
import com.aminography.model.city.City
import com.google.gson.Gson
import javax.inject.Inject

/**
 * @author aminography
 */
internal class JsonRetriever @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    fun readTo(fileName: String, inserter: Inserter<City>) {
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