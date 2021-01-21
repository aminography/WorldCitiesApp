package com.aminography.worldcities.ui.mapviewer.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * The factory class that takes required arguments for [MapViewerViewModel] and creates an instance
 * of it.
 *
 * @author aminography
 */
class MapViewerViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(clazz: Class<VM>): VM {
        if (clazz != MapViewerViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return MapViewerViewModel() as VM
    }
}
