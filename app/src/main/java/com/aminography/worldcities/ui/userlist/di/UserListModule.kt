package com.aminography.worldcities.ui.userlist.di

import com.aminography.scope.UserListScope
import com.aminography.worldcities.ui.userlist.UserListFragment
import com.aminography.worldcities.ui.userlist.vm.UserListViewModel
import com.aminography.worldcities.ui.userlist.vm.UserListViewModelFactory
import com.aminography.worldcities.ui.util.createViewModel
import dagger.Module
import dagger.Provides

/**
 * A dagger module class defining how to provide user-list related dependencies for injection.
 *
 * @author aminography
 */
@Module
class UserListModule {

    @UserListScope
    @Provides
    fun providesUserListViewModel(
        factory: UserListViewModelFactory,
        fragment: UserListFragment
    ): UserListViewModel = fragment.createViewModel(factory)
}