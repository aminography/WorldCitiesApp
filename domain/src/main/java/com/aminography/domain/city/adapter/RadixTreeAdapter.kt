package com.aminography.domain.city.adapter

import com.aminography.domain.ds.RadixTree
import com.aminography.model.City
import java.util.*

/**
 * @author aminography
 */
class RadixTreeAdapter(private val tree: RadixTree<City>) : Inserter<City> {
    override fun insert(element: City) {
        tree.insert(element.name.toLowerCase(Locale.getDefault()), element)
    }
}