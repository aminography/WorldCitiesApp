package com.aminography.worldcities.citylist.model

import com.aminography.model.city.City
import com.aminography.worldcities.citylist.adapter.CityItemDataHolder

/**
 * A mapper extension function that maps an instance of [City] to an instance of [CityItemDataHolder].
 *
 * @return an instance of [CityItemDataHolder] corresponding to the [City].
 *
 * @author aminography
 */
fun City.toCityItemDataHolder() =
    CityItemDataHolder(this)