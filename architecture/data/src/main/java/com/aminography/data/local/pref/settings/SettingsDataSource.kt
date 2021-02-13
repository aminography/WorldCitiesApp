package com.aminography.data.local.pref.settings

/**
 * @author aminography
 */
interface SettingsDataSource {

    var baseUrl: String

    fun clear()
}