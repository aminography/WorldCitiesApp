package com.aminography.core

import android.app.Application
import com.aminography.core.di.AppComponent

/**
 * @author aminography
 */
open class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponent.from(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        AppComponent.clear()
    }
}