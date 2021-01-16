package com.aminography.worldcities.ui.citylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aminography.worldcities.ui.base.adapter.BaseAdapter
import com.aminography.worldcities.ui.base.adapter.BaseViewHolder

/**
 * @author aminography
 */
class CityListAdapter : BaseAdapter() {

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, *> {
        return CityItemViewHolder(inflater, parent)
    }
}