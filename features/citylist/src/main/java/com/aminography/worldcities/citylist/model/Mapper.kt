package com.aminography.worldcities.citylist.model

import com.aminography.model.city.City
import com.aminography.worldcities.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.ui.model.MapViewerArg

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
 * A mapper extension function that maps an instance of [City] to an instance of [MapViewerArg].
 *
 * @return an instance of [MapViewerArg] corresponding to the [City].
 *
 * @author aminography
 */
fun City.toMapViewerArg() =
    MapViewerArg(name, country, coord)