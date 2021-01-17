package com.aminography.worldcities.ui.citylist.adapter

import com.aminography.model.city.City
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder

/**
 * @author aminography
 */
data class CityItemDataHolder(
    override val id: Int,
    val city: City
) : BaseDataHolder()

fun City.toCityItemDataHolder() = CityItemDataHolder(id, this)