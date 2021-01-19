package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.aminography.domain.base.onError
import com.aminography.domain.base.onLoading
import com.aminography.domain.base.onSuccess
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder
import com.aminography.worldcities.ui.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.ui.citylist.model.MapViewerArg
import com.aminography.worldcities.ui.citylist.model.toCityItemDataHolder
import com.aminography.worldcities.ui.citylist.model.toMapViewerArg
import com.aminography.worldcities.ui.util.SingleLiveEvent
import com.aminography.worldcities.ui.util.UniqueLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListViewModel @Inject constructor(
    application: Application,
    defaultDispatcher: CoroutineDispatcher,
    loadCitiesUseCase: LoadCitiesUseCase,
    searchCitiesUseCase: SearchCitiesUseCase,
    private val clearCitiesCacheUseCase: ClearCitiesCacheUseCase
) : AndroidViewModel(application) {

    private var loadCitiesJob: Job? = null
    private var searchCitiesJob: Job = Job()

    private val queryLiveData = UniqueLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _navigateToMap = SingleLiveEvent<MapViewerArg>()
    val navigateToMap: LiveData<MapViewerArg> = _navigateToMap

    val searchResult: LiveData<PagingData<CityItemDataHolder>> =
        queryLiveData.switchMap { query ->
            if (searchCitiesJob.isActive) {
                searchCitiesJob.cancel()
                searchCitiesJob = Job()
            }
            liveData(searchCitiesJob + defaultDispatcher) {
                _loading.postValue(true)
                searchCitiesUseCase(query)
                    .map { pagingData -> pagingData.map { it.toCityItemDataHolder() } }
                    .cachedIn(viewModelScope)
                    .flowOn(defaultDispatcher)
                    .collect {
                        if (loadCitiesJob?.isActive == false) {
                            _loading.postValue(false)
                            emit(it)
                        }
                    }
            }
        }

    fun setQuery(query: String) {
        queryLiveData.postValue(query)
    }

    fun onCityClicked(dataHolder: BaseDataHolder) {
        if (dataHolder is CityItemDataHolder) {
            _navigateToMap.postValue(dataHolder.toMapViewerArg())
        }
    }

    private fun reloadLastQuery() {
        val query = queryLiveData.value ?: ""
        queryLiveData.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        clearCitiesCacheUseCase(Unit)
    }

    init {
        loadCitiesJob = viewModelScope.launch(defaultDispatcher) {
            loadCitiesUseCase(Unit).collect {
                it.onLoading { _loading.postValue(true) }
                    .onSuccess { reloadLastQuery() }
                    .onError { e ->
                        _loading.postValue(false)
                        _errorMessage.postValue(e?.message ?: e.toString())
                    }
            }
        }
    }
}