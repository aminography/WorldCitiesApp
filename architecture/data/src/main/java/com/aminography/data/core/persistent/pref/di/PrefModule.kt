package com.aminography.data.core.persistent.pref.di

import android.content.Context
import com.aminography.data.KEY_SETTINGS_PREF
import com.aminography.data.core.persistent.pref.settings.SettingsDataSource
import com.aminography.data.core.persistent.pref.settings.SettingsDataSourceImpl
import com.aminography.scope.annotation.foundation.PersistentDataScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author aminography
 */
@Module
class PrefModule {

    @Provides
    @Named(KEY_SETTINGS_PREF)
    internal fun providesSettingsPref(): String = "settings-pref"

    @PersistentDataScope
    @Provides
    internal fun providesSettingsDataSource(
        context: Context,
        @Named(KEY_SETTINGS_PREF) fileName: String
    ): SettingsDataSource = SettingsDataSourceImpl(context, fileName)
}
