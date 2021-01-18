package com.aminography.worldcities.ui.mapviewer.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aminography.worldcities.MainApplication
import javax.inject.Inject

/**
 * @author aminography
 */
class MapViewerViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    override fun onCleared() {
        super.onCleared()
        getApplication<MainApplication>().mapViewerComponent = null
    }
}