package com.aminography.data.city.datasource.adapter

import com.aminography.domain.city.ds.RadixTree

/**
 * @author aminography
 */
class RadixTreeAdapter<T>(
    private val tree: RadixTree<T>,
    private inline val keyConverter: (T) -> String
) : Inserter<T> {

    override fun insert(element: T) {
        tree.insert(keyConverter(element), element)
    }
}