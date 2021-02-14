package com.aminography.worldcities.navigation

import com.aminography.navigation.BaseNavDestination
import com.aminography.navigation.argument.DeepLinkNavArg
import com.aminography.worldcities.navigation.model.MapViewerNavArg
import com.aminography.worldcities.navigation.model.UserListNavArg

/**
 * @author aminography
 */
sealed class NavDestinations<T : DeepLinkNavArg>(link: String) : BaseNavDestination<T>(link) {

    object TestDestinationWithNoArg : NavDestinations<Nothing>("worldcities://test_no_arg")

    object MapViewer : NavDestinations<MapViewerNavArg>("worldcities://map_viewer")

    object UserList : NavDestinations<UserListNavArg>("worldcities://user_list")
}
