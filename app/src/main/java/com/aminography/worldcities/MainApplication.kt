package com.aminography.worldcities

import android.app.Application
import android.os.StrictMode
import com.aminography.worldcities.di.AppComponent
import com.aminography.worldcities.di.DaggerAppComponent
import com.aminography.worldcities.ui.citylist.di.CityListComponent

/**
 * @author aminography
 */
class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    var cityListComponent: CityListComponent? = null

    override fun onCreate() {
        if (BuildConfig.DEBUG) enableStrictMode()
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }
}