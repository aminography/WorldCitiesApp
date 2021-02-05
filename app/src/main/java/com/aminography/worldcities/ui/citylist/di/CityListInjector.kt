package com.aminography.worldcities.ui.citylist.di

import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.util.componentManager

/**
 * An extension function on the [CityListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    componentManager?.also { cm ->
        cm.appComponent
            .plusCityListComponent()
            .cityListFragment(this)
            .build().also { cm.retainComponent(it) }
            .inject(this)
    }
}
