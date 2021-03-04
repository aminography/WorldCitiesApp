package com.aminography.worldcities.userlist.vm

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.aminography.domain.user.SearchUsersUseCase
import com.aminography.navigation.NavDirection
import com.aminography.worldcities.navigation.model.UserListNavArg
import com.aminography.worldcities.ui.util.SingleLiveEvent
import com.aminography.worldcities.ui.util.UniqueLiveData
import com.aminography.worldcities.userlist.adapter.UserItemDataHolder
import com.aminography.worldcities.userlist.model.toUserItemDataHolder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * @author aminography
 */
class UserListViewModel(
    private val defaultDispatcher: CoroutineDispatcher,
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private val _navigation = SingleLiveEvent<NavDirection>()
    val navigation: LiveData<NavDirection> = _navigation

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val queryLiveData = UniqueLiveData<String>()

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val searchResult: LiveData<PagingData<UserItemDataHolder>> =
        queryLiveData.switchMap { query ->
            liveData(defaultDispatcher) {
                searchUsersUseCase(query)
                    .map { pagingData -> pagingData.map { it.toUserItemDataHolder() } }
                    .cachedIn(viewModelScope)
                    .flowOn(defaultDispatcher)
                    .collect {
                        emit(it)
                    }
            }
        }

    fun setQuery(query: String) {
        queryLiveData.postValue(query)
    }

    fun init(navArg: UserListNavArg) {
        _cityName.postValue(navArg.city)
        setQuery(navArg.city)
    }

    fun onNavigateUpClicked() {
        _navigation.postValue(NavDirection.Up)
    }
}