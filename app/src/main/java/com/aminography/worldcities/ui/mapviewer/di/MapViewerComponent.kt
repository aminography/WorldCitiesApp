package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.scope.feature.MapViewerScope
import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [MapViewerScope].
 *
 * @author aminography
 */
@MapViewerScope
@Component(
    modules = [
        MapViewerModule::class
    ]
)
interface MapViewerComponent {

    fun inject(fragment: MapViewerFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fragment(fragment: MapViewerFragment): Builder

        fun build(): MapViewerComponent
    }
}