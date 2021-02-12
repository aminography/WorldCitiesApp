package com.aminography.worldcities.ui.citylist.di

import com.aminography.data.di.DaggerGsonComponent
import com.aminography.domain.di.DaggerDispatcherComponent
import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.util.application

/**
 * An extension function on the [CityListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    val appComponent = application!!.appComponent
    val dispatcherComponent = DaggerDispatcherComponent.builder().build()
    val gsonComponent = DaggerGsonComponent.builder().build()

    DaggerCityListComponent.builder()
        .appComponent(appComponent)
        .dispatcherComponent(dispatcherComponent)
        .gsonComponent(gsonComponent)
        .fragment(this)
        .build()
        .inject(this)
}
