package com.aminography.worldcities

import android.app.Application
import android.os.StrictMode
import com.aminography.worldcities.di.AppComponent
import com.aminography.worldcities.di.DaggerAppComponent

/**
 * The application class responsible for initializing dagger components and dependency graph.
 *
 * @author aminography
 */
class MainApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

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