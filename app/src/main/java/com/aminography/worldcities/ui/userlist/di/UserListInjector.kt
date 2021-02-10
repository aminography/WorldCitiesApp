package com.aminography.worldcities.ui.userlist.di

import com.aminography.worldcities.ui.userlist.UserListFragment
import com.aminography.worldcities.ui.util.application

/**
 * An extension function on the [UserListFragment] object to build related dagger component to
 * provide dependencies for this fragment.
 *
 * @author aminography
 */

fun UserListFragment.injectComponent() {
    application?.appComponent
        ?.plusUserListComponent()
        ?.fragment(this)
        ?.build()
        ?.inject(this)
}
