package com.aminography.worldcities.ui.userlist.di

import com.aminography.data.di.DaggerNetworkComponent
import com.aminography.domain.di.DaggerDispatcherComponent
import com.aminography.worldcities.ui.userlist.UserListFragment
import com.aminography.worldcities.ui.util.application

/**
 * An extension function on the [UserListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun UserListFragment.injectComponent() {
    val appComponent = application!!.appComponent
    val dispatcherComponent = DaggerDispatcherComponent.builder().build()
    val networkComponent = DaggerNetworkComponent.builder().appComponent(appComponent).build()

    DaggerUserListComponent.builder()
        .dispatcherComponent(dispatcherComponent)
        .networkComponent(networkComponent)
        .fragment(this)
        .build()
        .inject(this)
}
