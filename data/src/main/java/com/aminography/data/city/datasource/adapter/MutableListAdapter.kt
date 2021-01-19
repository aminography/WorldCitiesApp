package com.aminography.data.city.datasource.adapter

/**
 * @author aminography
 */
internal class MutableListAdapter<T>(
    private val list: MutableList<T>
) : Inserter<T> {

    override fun insert(element: T) {
        list.add(element)
    }
}