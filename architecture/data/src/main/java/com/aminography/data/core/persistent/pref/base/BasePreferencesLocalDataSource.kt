package com.aminography.data.core.persistent.pref.base

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * @author aminography
 */
internal open class BasePreferencesLocalDataSource(context: Context, fileName: String) {

    internal val pref: Lazy<SharedPreferences> = lazy {
        context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    fun clear() = pref.value.edit { clear() }
}
