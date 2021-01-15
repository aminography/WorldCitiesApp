package com.aminography.worldcities

import android.app.Application
import android.os.StrictMode
import com.aminography.worldcities.di.AppComponent
import com.aminography.worldcities.di.AppComponentProvider
import com.aminography.worldcities.di.DaggerAppComponent
import com.aminography.worldcities.di.context.DaggerContextComponent

/**
 * @author aminography
 */
class MainApplication : Application(), AppComponentProvider {

    override lateinit var appComponent: AppComponent

    override fun onCreate() {
        if (BuildConfig.DEBUG) enableStrictMode()
        super.onCreate()

        val contextComponent = DaggerContextComponent.factory().create(this)

        appComponent = DaggerAppComponent.factory()
            .create(contextComponent)
            .also { it.inject(this) }
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