package com.aminography.worldcities.citylist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.coroutine.di.DefaultDispatcher
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * The factory class that takes required arguments for [CityListViewModel] and creates an instance
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
