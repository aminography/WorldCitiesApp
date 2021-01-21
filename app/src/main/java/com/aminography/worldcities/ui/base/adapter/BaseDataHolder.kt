package com.aminography.worldcities.ui.base.adapter

/**
 * Base class for data-holders for feeding the adapter of the [androidx.recyclerview.widget.RecyclerView].
 *
 * @author aminography
 */
abstract class BaseDataHolder {

    /**
     * A unique id for each data-holder that should be provided by child classes.
     */
    abstract val id: Int
}