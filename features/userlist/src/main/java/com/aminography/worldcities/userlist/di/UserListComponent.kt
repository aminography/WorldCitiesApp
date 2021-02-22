package com.aminography.worldcities.userlist.di

import com.aminography.coroutine.di.CoroutineComponent
import com.aminography.data.core.network.NetworkComponent
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
        CoroutineComponent::class,
        NetworkComponent::class
    ],
    modules = [
        UserListModule::class
    ]
)
interface UserListComponent {

    fun inject(fragment: UserListFragment)

    @Component.Builder
    interface Builder {

        fun coroutineComponent(component: CoroutineComponent): Builder

        fun networkComponent(component: NetworkComponent): Builder

        @BindsInstance
        fun fragment(fragment: UserListFragment): Builder

        fun build(): UserListComponent
    }
}