package com.aminography.worldcities.ui.base.adapter

/**
 * @author aminography
 */
interface OnListItemLongClickListener {

    /**
     * @return true if the callback consumed the long click, false otherwise.
     */
    fun onItemLongClicked(dataHolder: BaseDataHolder): Boolean
}