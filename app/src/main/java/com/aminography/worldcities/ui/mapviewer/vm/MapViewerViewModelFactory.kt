package com.aminography.worldcities.ui.mapviewer.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * @author aminography
 */
class MapViewerViewModelFactory @Inject constructor(
    private val application: Application,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(clazz: Class<VM>): VM {
        if (clazz != MapViewerViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return MapViewerViewModel(
            application
        ) as VM
    }
}
