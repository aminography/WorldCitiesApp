package com.aminography.data.core.persistent.pref.base

import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author aminography
 */
internal class StringPreference(
    private val name: String,
    private val defaultValue: String
) : ReadWriteProperty<BasePreferencesLocalDataSource, String?> {

    override fun getValue(
        thisRef: BasePreferencesLocalDataSource,
        property: KProperty<*>
    ): String = thisRef.pref.value.getString(name, defaultValue) ?: defaultValue

    override fun setValue(
        thisRef: BasePreferencesLocalDataSource,
        property: KProperty<*>,
        value: String?
    ): Unit = thisRef.pref.value.edit { putString(name, value) }
}
