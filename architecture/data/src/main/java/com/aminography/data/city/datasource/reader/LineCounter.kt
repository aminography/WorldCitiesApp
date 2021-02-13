package com.aminography.data.city.datasource.reader

import android.content.Context
import com.aminography.data.util.openAsset
import com.aminography.data.util.toInputStreamReader
import com.aminography.data.util.toLineNumberReader
import javax.inject.Inject

/**
 * A utility class to count number of lines in a text file.
 *
 * @param context an instance of application [Context].
 *
 * @author aminography
 */
internal class LineCounter @Inject constructor(
    private val context: Context
) {

    /**
     * Reads the text file and returns number of lines.
     *
     * @param fileName the name of the text file, located in the `assets` directory.
     *
     * @return the number of lines in the text file.
     */
    suspend fun count(fileName: String): Int =
        context.openAsset(fileName)
            .toInputStreamReader()
            .toLineNumberReader()
            .use {
                it.skip(Long.MAX_VALUE)
                it.lineNumber + 1
            }
}