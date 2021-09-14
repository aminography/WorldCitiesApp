package com.aminography.data.util

import com.google.gson.Gson
import com.google.gson.stream.JsonReader

/**
 * @author aminography
 */

internal inline fun <reified T> Gson.fromJson(reader: JsonReader): T =
    fromJson(reader, T::class.java)
