package com.aminography.worldcities.ui.navigation

import androidx.core.net.toUri
import com.aminography.worldcities.ui.model.MapViewerNavArg
import com.aminography.worldcities.ui.model.UserListNavArg
import com.aminography.worldcities.ui.navigation.argument.DeepLinkNavArgument
import com.aminography.worldcities.ui.navigation.argument.encodeToBase64

/**
 * @author aminography
 */
sealed class NavDestination<T : DeepLinkNavArgument>(private val link: String) {

    object MapViewer : NavDestination<MapViewerNavArg>("myapp://map_viewer")
    object UserList : NavDestination<UserListNavArg>("myapp://user_list")

    //----------------------------------------------------------------------------------------------

    fun deepLink(): NavDirection.DeepLink =
        NavDirection.DeepLink(link.toUri())

    fun deepLinkWithArgument(navArgument: T): NavDirection.DeepLink =
        NavDirection.DeepLink(
            link.toUri()
                .buildUpon()
                .encodedQuery("arg=${navArgument.encodeToBase64}")
                .build()
        )
}
