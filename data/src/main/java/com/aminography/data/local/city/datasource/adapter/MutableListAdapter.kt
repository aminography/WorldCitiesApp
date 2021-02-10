package com.aminography.data.local.city.datasource.adapter

/**
 * An implementation of [Inserter] for adapting insertion interface for a [MutableList].
 *
 * @param list the wrapped [MutableList] by this adapter.
 *
 * @author aminography
 */
internal class MutableListAdapter<T>(
    private val list: MutableList<T>
) : Inserter<T> {

    override fun insert(element: T) {
        list.add(element)
    }
}