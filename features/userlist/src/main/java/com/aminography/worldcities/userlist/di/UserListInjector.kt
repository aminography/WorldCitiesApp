package com.aminography.worldcities.userlist.di

import com.aminography.coroutine.di.CoroutineComponent
import com.aminography.data.core.network.NetworkComponent
import com.aminography.worldcities.userlist.UserListFragment

/**
 * An extension function on the [UserListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun UserListFragment.injectComponent() {
    DaggerUserListComponent.builder()
        .coroutineComponent(CoroutineComponent.get())
        .networkComponent(NetworkComponent.get())
        .fragment(this)
        .build()
        .inject(this)
}
