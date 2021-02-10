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
 * A utility class to read a `json` file of cities, located in the `assets` directory and to insert
 * them into a data structure wrapped by [Inserter].
 *
 * @param context an instance of application [Context].
 * @param gson an instance of [Gson].
 *
 * @author aminography
 */
internal class JsonRetriever @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    /**
     * Reads the `json` file and add the cities into the data structure.
     *
     * @param fileName the name of the json file, located in the `assets` directory.
     * @param inserter the inserter-wrapped data structure.
     */
    suspend fun readTo(
        fileName: String,
        inserter: Inserter<City>,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): Unit = context.openAsset(fileName)
        .toInputStreamReader()
        .toJsonReader()
        .use {
            it.beginArray()
            for (i in 0 until offset) it.skipValue()

            var count = 0
            while (it.hasNext() && count < limit) {
                val city: City = gson.fromJson(it, City::class.java)
                inserter.insert(city)
                count++
            }
        }
}