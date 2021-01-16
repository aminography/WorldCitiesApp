package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.*
import com.aminography.domain.city.SearchCityRadixUseCase
import com.aminography.model.city.City
import com.aminography.model.common.onError
import com.aminography.model.common.onLoading
import com.aminography.model.common.onSuccess
import com.aminography.worldcities.MainApplication
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

    val queryCities: LiveData<List<City>> =
        queryLiveData.switchMap { query ->
            liveData {
                searchCityRadixUseCase(query)
                    .flowOn(defaultDispatcher)
                    .collect { result ->
                        result.onLoading { _loadingMessage.postValue(true) }
                            .onSuccess { emit(it!!) }
                            .onError { _errorMessage.postValue(it?.message ?: it.toString()) }
                    }
            }
        }

    fun setQuery(query: String) {
        queryLiveData.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<MainApplication>().cityListComponent = null
    }
}