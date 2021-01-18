package com.aminography.worldcities.ui.mapviewer.di

import com.aminography.worldcities.ui.mapviewer.MapViewerFragment
import com.aminography.worldcities.ui.mapviewer.vm.MapViewerViewModel
import com.aminography.worldcities.ui.mapviewer.vm.MapViewerViewModelFactory
import com.aminography.worldcities.ui.util.createViewModel
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class MapViewerModule {

    @Provides
    fun providesCityMapViewModel(
        factoryViewer: MapViewerViewModelFactory,
        viewerFragment: MapViewerFragment
    ): MapViewerViewModel = viewerFragment.createViewModel(factoryViewer)
}