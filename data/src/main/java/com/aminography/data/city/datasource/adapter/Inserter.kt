package com.aminography.data.city.datasource.adapter

/**
 * @author aminography
 */
interface Inserter<T> {

    fun insert(element: T)
}