package com.aminography.data.core.persistent.pref.settings

/**
 * @author aminography
 */
interface SettingsDataSource {

    var baseUrl: String

    fun clear()
}