package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.domain.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListViewModelFactory @Inject constructor(
    private val application: Application,
    private val loadCitiesUseCase: LoadCitiesUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(clazz: Class<VM>): VM {
        if (clazz != CityListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return CityListViewModel(
            application,
            defaultDispatcher,
            loadCitiesUseCase,
            searchCitiesUseCase
        ) as VM
    }
}
