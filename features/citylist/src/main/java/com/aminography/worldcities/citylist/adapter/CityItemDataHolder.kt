package com.aminography.worldcities.citylist.adapter

import com.aminography.model.city.City
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder

/**
 * The data-holder entity corresponding to each [City] instance for feeding [CityListAdapter].
 *
 * @param city an instance of [City] entity.
 *
 * @author aminography
 */
data class CityItemDataHolder(
    val city: City
) : BaseDataHolder() {

    override val id: Int
        get() = city.id
}