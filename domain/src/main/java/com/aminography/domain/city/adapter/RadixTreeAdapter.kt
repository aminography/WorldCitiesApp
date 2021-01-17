package com.aminography.domain.city.adapter

import com.aminography.domain.city.ds.RadixTree
import com.aminography.model.city.City
import java.util.*

/**
 * @author aminography
 */
class RadixTreeAdapter(private val tree: RadixTree<City>) : Inserter<City> {
    override fun insert(element: City) {
        tree.insert(element.name.toLowerCase(Locale.getDefault()), element)
    }
}