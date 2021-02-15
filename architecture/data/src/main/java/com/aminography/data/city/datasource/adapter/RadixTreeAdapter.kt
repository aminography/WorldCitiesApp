package com.aminography.data.city.datasource.adapter

import com.aminography.radixtree.MutableRadixTree
import com.aminography.radixtree.RadixTree

/**
 * An implementation of [Inserter] for adapting insertion interface for a [RadixTree].
 *
 * @param tree the wrapped [RadixTree] by this adapter.
 * @param keyTransformer the transformer function that maps an element to a key.
 *
 * @author aminography
 */
internal class RadixTreeAdapter<T>(
    private val tree: MutableRadixTree<T>,
    private inline val keyTransformer: (T) -> String
) : Inserter<T> {

    override fun insert(element: T) {
        tree.put(element, keyTransformer)
    }
}