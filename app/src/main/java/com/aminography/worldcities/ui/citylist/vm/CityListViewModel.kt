package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.model.common.onError
import com.aminography.model.common.onLoading
import com.aminography.model.common.onSuccess
import com.aminography.worldcities.MainApplication
import com.aminography.worldcities.ui.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.ui.citylist.adapter.toCityItemDataHolder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * @author aminography
 */
class CityListViewModel(
    application: Application,
    defaultDispatcher: CoroutineDispatcher,
    loadCitiesUseCase: LoadCitiesUseCase,
    searchCitiesUseCase: SearchCitiesUseCase,
    private val clearCitiesCacheUseCase: ClearCitiesCacheUseCase
) : AndroidViewModel(application) {

    private var loadCitiesJob: Job? = null

    private val queryLiveData = MutableLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val searchResult: LiveData<PagingData<CityItemDataHolder>> =
        queryLiveData.switchMap { query ->
            liveData(defaultDispatcher) {
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

    private fun reloadLastQuery() {
        val query = queryLiveData.value ?: ""
        queryLiveData.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<MainApplication>().cityListComponent = null
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