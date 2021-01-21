package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import com.aminography.worldcities.ui.util.application

/**
 * An extension function on the [MapViewerFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */
fun MapViewerFragment.injectComponent() {
    application?.appComponent
        ?.plusCityMapComponent()
        ?.cityMapFragment(this)
        ?.build()
        ?.inject(this)
}
