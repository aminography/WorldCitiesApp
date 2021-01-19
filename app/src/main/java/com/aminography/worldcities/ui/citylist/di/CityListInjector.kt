package com.aminography.worldcities.ui.citylist.di

import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.util.application

/**
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    application?.appComponent
        ?.plusCityListComponent()
        ?.cityListFragment(this)
        ?.build()
        ?.inject(this)
}
