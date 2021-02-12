package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.scope.annotation.FeatureScope
import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [FeatureScope].
 *
 * @author aminography
 */
@FeatureScope
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