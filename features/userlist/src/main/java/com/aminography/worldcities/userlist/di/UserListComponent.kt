package com.aminography.worldcities.userlist.di

import com.aminography.data.di.NetworkComponent
import com.aminography.data.user.di.UserDataModule
import com.aminography.domain.di.DispatcherComponent
import com.aminography.scope.annotation.FeatureScope
import com.aminography.worldcities.userlist.UserListFragment
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [FeatureScope].
 *
 * @author aminography
 */
@FeatureScope
@Component(
    dependencies = [
        DispatcherComponent::class,
        NetworkComponent::class
    ],
    modules = [
        UserListModule::class,
        UserDataModule::class
    ]
)
interface UserListComponent {

    fun inject(fragment: UserListFragment)

    @Component.Builder
    interface Builder {

        fun dispatcherComponent(component: DispatcherComponent): Builder

        fun networkComponent(component: NetworkComponent): Builder

        @BindsInstance
        fun fragment(fragment: UserListFragment): Builder

        fun build(): UserListComponent
    }
}