package com.aminography.worldcities.ui.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Base class for view-holders that defines a common functionality of setting a [BaseDataHolder]
 * instance and binding it to the view.
 *
 * @author aminography
 */
abstract class BaseViewHolder<DH : BaseDataHolder, VB : ViewBinding>(
    protected val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * An instance of [BaseDataHolder] to be bind to the view.
     */
    var dataHolder: DH? = null
        internal set(value) {
            field = value
            value?.let { bindDataToView(it) }
        }

    /**
     * The binding function that binds data from [dataHolder] to the view.
     *
     * @param dataHolder an instance of [BaseDataHolder] to be bind to the view.
     */
    abstract fun bindDataToView(dataHolder: DH)
}