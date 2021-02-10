package com.aminography.data.local.pref.di

import android.content.Context
import com.aminography.data.local.pref.settings.SettingsDataSource
import com.aminography.data.local.pref.settings.SettingsDataSourceImpl
import com.aminography.scope.AppScope
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class PrefModule {

    @AppScope
    @Provides
    internal fun providesSettingsLocalDataSource(
        context: Context
    ): SettingsDataSource = SettingsDataSourceImpl(context)
}
