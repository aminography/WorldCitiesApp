package com.aminography.domain.city.adapter

import com.aminography.model.City

/**
 * @author aminography
 */
class MutableListAdapter(private val list: MutableList<City>) : Inserter<City> {
    override fun insert(element: City) {
        list.add(element)
    }
}