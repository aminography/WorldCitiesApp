package com.aminography.worldcities.citylist.di

import com.aminography.core.di.AppComponent
import com.aminography.coroutine.di.CoroutineComponent
import com.aminography.data.core.gson.GsonComponent
import com.aminography.worldcities.citylist.CityListFragment

/**
 * An extension function on the [CityListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    DaggerCityListComponent.builder()
        .appComponent(AppComponent.get())
        .coroutineComponent(CoroutineComponent.get())
        .gsonComponent(GsonComponent.get())
        .fragment(this)
        .build()
        .inject(this)
}
