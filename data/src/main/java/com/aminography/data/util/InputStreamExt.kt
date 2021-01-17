package com.aminography.data.util

import android.content.Context
import com.google.gson.stream.JsonReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * @author aminography
 */

internal fun Context.openAsset(fileName: String): InputStream = assets.open(fileName)

internal fun InputStream.toInputStreamReader() = InputStreamReader(this, charset("UTF-8"))

internal fun InputStreamReader.toJsonReader() = JsonReader(this)
