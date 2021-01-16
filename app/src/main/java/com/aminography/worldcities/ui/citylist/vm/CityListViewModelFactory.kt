package com.aminography.worldcities.ui.citylist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        if (modelClass != CityListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CityListViewModel() as VM
    }
}
