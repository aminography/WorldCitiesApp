package com.aminography.worldcities.ui.citylist.di

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
            .build()
            .also { component -> it.cityListComponent = component }
            .inject(this)
    }
}
