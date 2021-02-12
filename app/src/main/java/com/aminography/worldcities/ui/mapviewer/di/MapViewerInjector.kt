package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.worldcities.ui.mapviewer.MapViewerFragment

/**
 * An extension function on the [MapViewerFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */
fun MapViewerFragment.injectComponent() {
    DaggerMapViewerComponent.builder()
        .fragment(this)
        .build()
        .inject(this)
}
