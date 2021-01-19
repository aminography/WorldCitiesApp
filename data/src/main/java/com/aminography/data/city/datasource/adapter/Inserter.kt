package com.aminography.data.city.datasource.adapter

/**
 * @author aminography
 */
internal interface Inserter<T> {

    fun insert(element: T)
}