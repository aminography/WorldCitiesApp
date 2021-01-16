package com.aminography.worldcities.ui.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author aminography
 */
abstract class BaseViewHolder<DH : BaseDataHolder, VB : ViewBinding>(
    protected val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    var dataHolder: DH? = null
        internal set(value) {
            field = value
            value?.let { bindDataToView(it) }
        }

    abstract fun bindDataToView(dataHolder: DH)
}