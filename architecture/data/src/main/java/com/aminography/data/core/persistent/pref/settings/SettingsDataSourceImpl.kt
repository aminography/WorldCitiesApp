package com.aminography.data.core.persistent.pref.settings

import android.content.Context
import com.aminography.data.core.persistent.pref.base.BasePreferencesLocalDataSource
import com.aminography.data.core.persistent.pref.base.StringPreference
import javax.inject.Inject

/**
 * @author aminography
 */
internal class SettingsDataSourceImpl @Inject constructor(
    context: Context,
    fileName: String
) : BasePreferencesLocalDataSource(context, fileName), SettingsDataSource {

    override var baseUrl by StringPreference("BASE_URL", "https://api.github.com/")
}
