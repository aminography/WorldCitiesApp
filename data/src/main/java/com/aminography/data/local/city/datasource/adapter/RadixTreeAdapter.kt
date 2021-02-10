package com.aminography.data.local.city.datasource.adapter

import com.aminography.domain.city.ds.RadixTree

/**
 * An implementation of [Inserter] for adapting insertion interface for a [RadixTree].
 *
 * @param tree the wrapped [RadixTree] by this adapter.
 * @param keyConverter the converter function that maps an element to a key.
 *
 * @author aminography
 */
internal class RadixTreeAdapter<T>(
    private val tree: RadixTree<T>,
    private inline val keyConverter: (T) -> String
) : Inserter<T> {

    override fun insert(element: T) {
        tree.insert(keyConverter(element), element)
    }
}