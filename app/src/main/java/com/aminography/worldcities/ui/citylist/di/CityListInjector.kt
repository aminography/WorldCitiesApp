package com.aminography.worldcities.ui.citylist.di

import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.util.application

/**
 * An extension function on the [CityListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun CityListFragment.injectComponent() {
    application?.appComponent
        ?.plusCityListComponent()
        ?.fragment(this)
        ?.build()
        ?.inject(this)
}
