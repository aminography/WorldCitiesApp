package com.aminography.data.core.persistent.pref.settings

import android.content.Context
import com.aminography.data.core.persistent.pref.base.BasePreferencesLocalDataSource
import com.aminography.data.core.persistent.pref.base.StringPreference
import javax.inject.Inject

/**
 * @author aminography
 */
internal class SettingsDataSourceImpl @Inject constructor(
    context: Context
) : BasePreferencesLocalDataSource(context, PREF_FILE_NAME), SettingsDataSource {

    override var baseUrl by StringPreference(pref, KEY_BASE_URL, "https://api.github.com/")

    companion object {
        private const val PREF_FILE_NAME = "settings-pref"
        private const val KEY_BASE_URL = "BASE_URL"
    }
}
