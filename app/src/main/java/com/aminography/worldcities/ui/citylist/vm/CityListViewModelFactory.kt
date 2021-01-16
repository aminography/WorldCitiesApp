package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.domain.city.GetCityTreeFlowUseCase
import com.aminography.domain.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListViewModelFactory @Inject constructor(
    private val application: Application,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val getCityTreeFlowUseCase: GetCityTreeFlowUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
        if (modelClass != CityListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CityListViewModel(application, defaultDispatcher, getCityTreeFlowUseCase) as VM
    }
}
