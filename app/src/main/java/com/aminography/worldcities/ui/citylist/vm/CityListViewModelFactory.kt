package com.aminography.worldcities.ui.citylist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.domain.di.DefaultDispatcher
import com.aminography.worldcities.ui.mapviewer.vm.MapViewerViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * The factory class that takes required arguments for [MapViewerViewModel] and creates an instance
 * of it.
 *
 * @author aminography
 */
class CityListViewModelFactory @Inject constructor(
    private val loadCitiesUseCase: LoadCitiesUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val clearCitiesCacheUseCase: ClearCitiesCacheUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(clazz: Class<VM>): VM {
        if (clazz != CityListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CityListViewModel(
            defaultDispatcher,
            loadCitiesUseCase,
            searchCitiesUseCase,
            clearCitiesCacheUseCase
        ) as VM
    }
}
