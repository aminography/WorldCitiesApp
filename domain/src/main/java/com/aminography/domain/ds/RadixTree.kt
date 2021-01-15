package com.aminography.domain.ds

import java.util.*

/**
 * @author aminography
 */
interface RadixTree<T> {

    fun insert(key: String, value: T, replace: Boolean = false)

    fun searchPrefix(prefix: String): ArrayList<T>

    val size: Int
}