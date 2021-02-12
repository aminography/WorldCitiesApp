package com.aminography.worldcities.ui.userlist.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminography.domain.user.SearchUsersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author aminography
 */
class UserListViewModel @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher,
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    fun init(cityName: String) {
        _cityName.postValue(cityName)
        viewModelScope.launch(defaultDispatcher) {
            searchUsersUseCase(cityName).collect {
                println("XXXXX: $it")
            }
        }
    }
}