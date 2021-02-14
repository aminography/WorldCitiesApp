package com.aminography.worldcities

import android.os.StrictMode
import com.aminography.core.CoreApplication

/**
 * The application class responsible for initializing dagger components and dependency graph.
 *
 * @author aminography
 */
class MainApplication : CoreApplication() {

    override fun onCreate() {
        if (BuildConfig.DEBUG) enableStrictMode()
        super.onCreate()
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