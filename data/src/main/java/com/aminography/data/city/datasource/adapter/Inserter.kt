package com.aminography.data.city.datasource.adapter

/**
 * A common interface to unify insertion mechanism for different data structure by using
 * [Adapter](https://en.wikipedia.org/wiki/Adapter_pattern) design pattern.
 *
 * @param T the type of elements to be inserted.
 *
 * @author aminography
 */
internal interface Inserter<T> {

    /**
     * Inserts the element into the data structure
     *
     * @param element the element to be inserted
     */
    fun insert(element: T)
}