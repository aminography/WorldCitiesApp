package com.aminography.worldcities.userlist.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminography.domain.user.SearchUsersUseCase
import com.aminography.worldcities.ui.model.UserListNavArg
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author aminography
 */
class UserListViewModel(
    private val defaultDispatcher: CoroutineDispatcher,
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    fun init(navArg: UserListNavArg) {
        _cityName.postValue(navArg.city)
        viewModelScope.launch(defaultDispatcher) {
            searchUsersUseCase(navArg.city).collect {
                println("XXXXX: $it")
            }
        }
    }
}