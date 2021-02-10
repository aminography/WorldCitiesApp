package com.aminography.worldcities.ui.userlist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.domain.di.DefaultDispatcher
import com.aminography.domain.user.SearchUsersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * The factory class that takes required arguments for [UserListViewModel] and creates an instance
 * of it.
 *
 * @author aminography
 */
class UserListViewModelFactory @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <VM : ViewModel> create(clazz: Class<VM>): VM {
        if (clazz != UserListViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return UserListViewModel(
            defaultDispatcher,
            searchUsersUseCase
        ) as VM
    }
}
