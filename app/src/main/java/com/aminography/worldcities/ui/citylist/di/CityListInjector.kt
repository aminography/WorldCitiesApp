package com.aminography.worldcities.ui.citylist.di

import com.aminography.data.city.di.CityDataModule
import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.util.application

/**
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    application?.let {
        it.appComponent
            .plusCityListComponent()
            .cityListFragment(this)
            .cityListModule(CityListModule())
            .cityDataModule(CityDataModule())
            .build()
            .also { component -> it.cityListComponent = component }
            .inject(this)
    }
}

fun CityListFragment.clearComponent() {
    application?.cityListComponent = null
}
