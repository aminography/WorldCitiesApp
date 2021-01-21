package com.aminography.worldcities.ui.citylist.model

import com.aminography.model.city.City
import com.aminography.worldcities.ui.citylist.adapter.CityItemDataHolder

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
 * A mapper extension function that maps an instance of [CityItemDataHolder] to an instance of
 * [MapViewerArg].
 *
 * @return an instance of [MapViewerArg] corresponding to the [CityItemDataHolder].
 *
 * @author aminography
 */
fun CityItemDataHolder.toMapViewerArg() =
    city.run { MapViewerArg(name, country, coord) }