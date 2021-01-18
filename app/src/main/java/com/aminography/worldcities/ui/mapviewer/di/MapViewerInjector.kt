package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import com.aminography.worldcities.ui.util.application

/**
 * @author aminography
 */

fun MapViewerFragment.injectComponent() {
    application?.let {
        it.appComponent
            .plusCityMapComponent()
            .cityMapFragment(this)
            .build()
            .also { component -> it.mapViewerComponent = component }
            .inject(this)
    }
}
