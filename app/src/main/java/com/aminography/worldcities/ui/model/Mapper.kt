package com.aminography.worldcities.ui.model

import com.aminography.model.city.City

/**
 * A mapper extension function that maps an instance of [City] to an instance of [MapViewerNavArg].
 *
 * @return an instance of [MapViewerNavArg] corresponding to the [City].
 *
 * @author aminography
 */
fun City.toMapViewerNavArg() =
    MapViewerNavArg(name, country, coord)

fun City.toUserListNavArg() =
    UserListNavArg(name)