package com.aminography.worldcities.ui.base.adapter

/**
 * A listener interface to handle click events on the [androidx.recyclerview.widget.RecyclerView] items.
 *
 * @author aminography
 */
interface OnListItemClickListener {

    /**
     * Called when an item of the [androidx.recyclerview.widget.RecyclerView] is getting clicked.
     *
     * @param dataHolder the [BaseDataHolder] instance corresponding to the clicked view-holder.
     */
    fun onItemClicked(dataHolder: BaseDataHolder?)
}