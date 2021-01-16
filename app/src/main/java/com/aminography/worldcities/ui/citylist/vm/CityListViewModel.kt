package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.*
import com.aminography.domain.city.SearchCityRadixUseCase
import com.aminography.domain.util.mapListInResult
import com.aminography.model.common.onError
import com.aminography.model.common.onLoading
import com.aminography.model.common.onSuccess
import com.aminography.worldcities.MainApplication
import com.aminography.worldcities.ui.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.ui.citylist.adapter.toCityItemDataHolder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

/**
 * @author aminography
 */
class CityListViewModel(
    application: Application,
    defaultDispatcher: CoroutineDispatcher,
    searchCityRadixUseCase: SearchCityRadixUseCase
) : AndroidViewModel(application) {

    private val queryLiveData = MutableLiveData<String>()

    private val _loadingMessage = MutableLiveData<Boolean>()
    val loadingMessage: LiveData<Boolean> = _loadingMessage

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val queryCities: LiveData<List<CityItemDataHolder>> =
        queryLiveData.switchMap { query ->
            liveData {
                searchCityRadixUseCase(query)
                    .mapListInResult { it.toCityItemDataHolder() }
                    .flowOn(defaultDispatcher)
                    .collect { result ->
                        result.onLoading { _loadingMessage.postValue(true) }
                            .onSuccess { emit(it ?: listOf<CityItemDataHolder>()) }
                            .onError { _errorMessage.postValue(it?.message ?: it.toString()) }
                    }
            }
        }

    fun setQuery(query: String) {
        queryLiveData.postValue(if (query.isBlank()) "*" else query)
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<MainApplication>().cityListComponent = null
    }
}