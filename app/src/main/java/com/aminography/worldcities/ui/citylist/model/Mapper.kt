package com.aminography.worldcities.ui.citylist.model

import com.aminography.model.city.City
import com.aminography.worldcities.ui.citylist.adapter.CityItemDataHolder

/**
 * @author aminography
 */

fun City.toCityItemDataHolder() =
    CityItemDataHolder(id, this)

fun CityItemDataHolder.toMapViewerArg() =
    city.run { MapViewerArg(name, country, coord) }