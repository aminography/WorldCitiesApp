package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.scope.CityMapScope
import com.aminography.worldcities.ui.citylist.di.CityListModule
import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * @author aminography
 */
@CityMapScope
@Subcomponent(
    modules = [
        CityListModule::class
    ]
)
interface MapViewerComponent {

    fun inject(viewerFragment: MapViewerFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): MapViewerComponent

        @BindsInstance
        fun cityMapFragment(viewerFragment: MapViewerFragment): Builder
    }
}