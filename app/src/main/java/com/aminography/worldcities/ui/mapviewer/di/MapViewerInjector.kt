package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import com.aminography.worldcities.ui.util.componentManager

/**
 * An extension function on the [MapViewerFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */
fun MapViewerFragment.injectComponent() {
    componentManager?.also { cm ->
        cm.appComponent
            .plusCityMapComponent()
            .cityMapFragment(this)
            .build().also { cm.retainComponent(it) }
            .inject(this)
    }
}
