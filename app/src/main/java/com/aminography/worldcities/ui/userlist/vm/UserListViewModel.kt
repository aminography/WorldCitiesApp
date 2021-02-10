package com.aminography.worldcities.ui.userlist.vm

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
    defaultDispatcher: CoroutineDispatcher,
    searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            searchUsersUseCase("tehran").collect {
                println("XXXXX: $it")
            }
        }
    }
}