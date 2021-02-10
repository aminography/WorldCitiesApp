package com.aminography.worldcities.ui.userlist.di

import com.aminography.data.remote.user.di.UserDataModule
import com.aminography.scope.UserListScope
import com.aminography.worldcities.ui.userlist.UserListFragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * A dagger component class providing dependencies related to the [UserListScope].
 *
 * @author aminography
 */
@UserListScope
@Subcomponent(
    modules = [
        UserListModule::class,
        UserDataModule::class
    ]
)
interface UserListComponent {

    fun inject(fragment: UserListFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): UserListComponent

        @BindsInstance
        fun fragment(fragment: UserListFragment): Builder
    }
}