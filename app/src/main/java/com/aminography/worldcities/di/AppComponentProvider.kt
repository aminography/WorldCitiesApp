package com.aminography.worldcities.di

import android.content.Context

/**
 * @author aminography
 */
interface AppComponentProvider {

    val appComponent: AppComponent
}

val Context.appComponent: AppComponent
    get() = (applicationContext as AppComponentProvider).appComponent