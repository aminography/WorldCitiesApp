package com.aminography.worldcities.userlist.di

import com.aminography.scope.annotation.FeatureScope
import com.aminography.worldcities.ui.util.createViewModel
import com.aminography.worldcities.userlist.UserListFragment
import com.aminography.worldcities.userlist.vm.UserListViewModel
import com.aminography.worldcities.userlist.vm.UserListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * A dagger module class defining how to provide user-list related dependencies for injection.
 *
 * @author aminography
 */
@Module
class UserListModule {

    @FeatureScope
    @Provides
    fun providesUserListViewModel(
        factory: UserListViewModelFactory,
        fragment: UserListFragment
    ): UserListViewModel = fragment.createViewModel(factory)
}