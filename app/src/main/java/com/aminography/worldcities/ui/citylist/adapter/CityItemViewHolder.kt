package com.aminography.worldcities.ui.citylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aminography.worldcities.databinding.ListItemCityBinding
import com.aminography.worldcities.ui.base.adapter.BaseViewHolder

/**
 * The view-holder class to represent each city data-holder in the [androidx.recyclerview.widget.RecyclerView].
 *
 * @param inflater an instance of [LayoutInflater].
 * @param parent the parent [ViewGroup] to to attach the inflated view to it.
 *
 * @author aminography
 */
class CityItemViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<CityItemDataHolder, ListItemCityBinding>(
    ListItemCityBinding.inflate(inflater, parent, false)
) {

    override fun bindDataToView(dataHolder: CityItemDataHolder) = with(binding) {
        nameTextView.text = dataHolder.city.run { "$name, $country" }
        coordTextView.text = dataHolder.city.coord.run { "($lat, $lon)" }
    }
}