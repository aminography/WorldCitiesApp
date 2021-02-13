package com.aminography.worldcities.citylist.model

import com.aminography.model.city.City
import com.aminography.worldcities.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.ui.model.MapViewerNavArg
import com.aminography.worldcities.ui.model.UserListNavArg

/**
 * A mapper extension function that maps an instance of [City] to an instance of [CityItemDataHolder].
 *
 * @return an instance of [CityItemDataHolder] corresponding to the [City].
 *
 * @author aminography
 */
fun City.toCityItemDataHolder() =
    CityItemDataHolder(this)

/**
 * A mapper extension function that maps an instance of [City] to an instance of [MapViewerNavArg].
 *
 * @return an instance of [MapViewerNavArg] corresponding to the [City].
 *
 * @author aminography
 */
fun City.toMapViewerArg() =
    MapViewerNavArg(name, country, coord)

fun City.toUserListArg() =
    UserListNavArg(name)