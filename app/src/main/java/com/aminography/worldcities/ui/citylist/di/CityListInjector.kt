package com.aminography.worldcities.ui.citylist.di

import com.aminography.core.di.AppComponent
import com.aminography.data.di.GsonComponent
import com.aminography.domain.di.DispatcherComponent
import com.aminography.worldcities.ui.citylist.CityListFragment

/**
 * An extension function on the [CityListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    DaggerCityListComponent.builder()
        .appComponent(AppComponent.get())
        .dispatcherComponent(DispatcherComponent.get())
        .gsonComponent(GsonComponent.get())
        .fragment(this)
        .build()
        .inject(this)
}
