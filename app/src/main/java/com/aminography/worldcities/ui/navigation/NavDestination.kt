package com.aminography.worldcities.ui.navigation

import android.net.Uri
import androidx.core.net.toUri
import com.aminography.worldcities.ui.util.encodeToBase64

/**
 * @author aminography
 */
sealed class NavDestination(private val deepLink: String) {

    object MapViewer : NavDestination("myapp://map_viewer")
    object UserList : NavDestination("myapp://user_list")

    fun deepLink(): Uri = deepLink.toUri()

    fun deepLinkWithArgument(arg: NavArgument): Uri =
        deepLink.toUri()
            .buildUpon()
            .encodedQuery("arg=${arg.encodeToBase64}")
            .build()
}
