package com.aminography.core

import android.app.Application
import com.aminography.core.di.AppComponent
import com.aminography.core.di.DaggerAppComponent

/**
 * @author aminography
 */
open class CoreApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(applicationContext)
    }
}