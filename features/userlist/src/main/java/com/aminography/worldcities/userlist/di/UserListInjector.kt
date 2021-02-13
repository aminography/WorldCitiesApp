package com.aminography.worldcities.userlist.di

import com.aminography.data.di.NetworkComponent
import com.aminography.domain.di.DispatcherComponent
import com.aminography.worldcities.userlist.UserListFragment

/**
 * An extension function on the [UserListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun UserListFragment.injectComponent() {
    DaggerUserListComponent.builder()
        .dispatcherComponent(DispatcherComponent.get())
        .networkComponent(NetworkComponent.get())
        .fragment(this)
        .build()
        .inject(this)
}
