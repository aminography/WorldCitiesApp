package com.aminography.worldcities.navigation

import android.os.Bundle
import android.os.Parcelable
import com.aminography.navigation.BaseNavDestination
import com.aminography.worldcities.navigation.model.MapViewerNavArg
import com.aminography.worldcities.navigation.model.UserListNavArg

/**
 * @author aminography
 */
sealed class NavDestinations<T : Parcelable>(link: String) : BaseNavDestination<T>(link) {

    object TestDestinationWithBundleArg : NavDestinations<Bundle>("worldcities://test_bundle_arg")

    object CityList : NavDestinations<Nothing>("worldcities://city_list")

    object MapViewer : NavDestinations<MapViewerNavArg>("worldcities://map_viewer")

    object UserList : NavDestinations<UserListNavArg>("worldcities://user_list")
}
