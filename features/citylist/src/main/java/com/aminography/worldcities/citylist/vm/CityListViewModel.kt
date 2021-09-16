package com.aminography.worldcities.citylist.vm

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.aminography.coroutine.SingleRunningJob
import com.aminography.coroutine.util.cancelIfActive
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.domain.city.SelectCityUseCase
import com.aminography.model.city.City
import com.aminography.navigation.NavDirection
import com.aminography.worldcities.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.citylist.model.toCityItemDataHolder
import com.aminography.worldcities.citylist.model.toMapViewerNavArg
import com.aminography.worldcities.navigation.NavDestinations
import com.aminography.worldcities.ui.util.SingleLiveEvent
import com.aminography.worldcities.ui.util.UniqueLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 *  The [ViewModel] class fo the city-list user interface.
 *
 * @param defaultDispatcher the default [CoroutineDispatcher] to be used for launching coroutines.
 * @param loadCitiesUseCase an instance of [LoadCitiesUseCase] to load cities from file.
 * @param searchCitiesUseCase an instance of [SearchCitiesUseCase] to perform prefix search on cities.
 * @param clearCitiesCacheUseCase an instance of [ClearCitiesCacheUseCase] to lear cache of cities.
 *
 * @author aminography
 */
class CityListViewModel(
    defaultDispatcher: CoroutineDispatcher,
    loadCitiesUseCase: LoadCitiesUseCase,
    searchCitiesUseCase: SearchCitiesUseCase,
    private val selectCityUseCase: SelectCityUseCase,
    private val clearCitiesCacheUseCase: ClearCitiesCacheUseCase
) : ViewModel() {

    private val _navigation = SingleLiveEvent<NavDirection>()
    val navigation: LiveData<NavDirection> = _navigation

    private var loadCitiesJob: Job by SingleRunningJob()
    private var searchCitiesJob: Job by SingleRunningJob()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val queryLiveData = UniqueLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val searchResult: LiveData<PagingData<CityItemDataHolder>> =
        queryLiveData.switchMap { query ->
            // cancels previous uncompleted search, if any
            searchCitiesJob = Job()
            liveData(defaultDispatcher + searchCitiesJob) {
                _loading.postValue(true)
                searchCitiesUseCase(query)
                    .map { pagingData -> pagingData.map { it.toCityItemDataHolder() } }
                    .cachedIn(viewModelScope)
                    .flowOn(defaultDispatcher)
                    .collect {
                        // posts the result if loading of the cities has been completed
                        if (!loadCitiesJob.isActive) {
                            _loading.postValue(false)
                            emit(it)
                        }
                    }
            }
        }

    /**
     * Takes a [query] string to perform a prefix search on cities.
     *
     * @param query the prefix of cities to be retrieved.
     */
    fun setQuery(query: String) {
        queryLiveData.postValue(query)
    }

    /**
     * Decides an action when a list item is clicked.
     *
     * @param city the [City] corresponding to the clicked item.
     */
    fun onCityClicked(city: City) {
        viewModelScope.launch {
            selectCityUseCase(city)
                .fold(
                    onSuccess = {
                        _navigation.postValue(
                            NavDestinations.MapViewer.deepLinkWithArg(
                                city.toMapViewerNavArg()
                            )
                        )
                    },
                    onFailure = { _errorMessage.postValue(it.message ?: it.toString()) }
                )
        }
    }

    /**
     * Searches the last query again that is ignored during the loading cities.
     */
    private fun reloadLastQuery() {
        val query = queryLiveData.value ?: ""
        queryLiveData.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        searchCitiesJob.cancelIfActive()
        GlobalScope.launch { clearCitiesCacheUseCase(Unit) }
    }

    init {
        loadCitiesJob = viewModelScope.launch {
            _loading.postValue(true)
            loadCitiesUseCase(Unit)
                .fold(
                    onSuccess = {
                        reloadLastQuery()
                        loadCitiesJob.cancel()
                    },
                    onFailure = {
                        _loading.postValue(false)
                        _errorMessage.postValue(it.message ?: it.toString())
                    }
                )
        }
    }
}
