package com.aminography.data.core.persistent.pref.di

import android.content.Context
import com.aminography.data.core.persistent.pref.settings.SettingsDataSource
import com.aminography.data.core.persistent.pref.settings.SettingsDataSourceImpl
import com.aminography.scope.annotation.foundation.PersistentDataScope
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class PrefModule {

    @PersistentDataScope
    @Provides
    internal fun providesSettingsDataSource(
        context: Context
    ): SettingsDataSource = SettingsDataSourceImpl(context)
}
