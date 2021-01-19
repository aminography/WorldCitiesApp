package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import com.aminography.worldcities.ui.util.application

/**
 * @author aminography
 */

fun MapViewerFragment.injectComponent() {
    application?.appComponent
        ?.plusCityMapComponent()
        ?.cityMapFragment(this)
        ?.build()
        ?.inject(this)
}
