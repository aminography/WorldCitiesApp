@file:Suppress("BlockingMethodInNonBlockingContext")

package com.aminography.data.util

import android.content.Context
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author aminography
 */

internal suspend fun Context.openAsset(fileName: String): InputStream =
    withContext(Dispatchers.IO) { assets.open(fileName) }

internal suspend fun JsonReader.beginArraySuspending() =
    withContext(Dispatchers.IO) { beginArray() }

internal suspend fun JsonReader.hasNextSuspending() =
    withContext(Dispatchers.IO) { hasNext() }

internal fun InputStream.toInputStreamReader() = InputStreamReader(this, charset("UTF-8"))

internal fun InputStreamReader.toJsonReader() = JsonReader(this)
