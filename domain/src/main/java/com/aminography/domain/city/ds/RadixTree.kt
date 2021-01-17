package com.aminography.domain.city.ds

/**
 * @author aminography
 */
interface RadixTree<T> {

    fun insert(key: String, value: T, replace: Boolean = false)

    fun searchPrefix(prefix: String, offset: Int = 0, limit: Int = Int.MAX_VALUE): List<T>

    fun toList(): List<T>

    val size: Int
}