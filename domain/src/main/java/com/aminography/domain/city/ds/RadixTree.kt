package com.aminography.domain.city.ds

/**
 * The abstraction of the radix tree that defines expected functions for the concrete child class.
 *
 * @param T the type of the elements contained in the tree.
 *
 * @author aminography
 */
interface RadixTree<T> {

    /**
     * Inserts the specified element regarding its key to the tree.
     *
     * @param key the key of the element to be inserted.
     * @param value the element to be inserted.
     * @param replace if the replace is true and there exists another element with the same key, the
     * value will replaces the old one.
     */
    fun insert(key: String, value: T, replace: Boolean = false)

    /**
     * Performs a prefix search on the tree.
     *
     * @param prefix the prefix of element keys to be retrieved.
     * @param offset the starting offset of the elements in the result, used for handling pagination.
     * @param limit the maximum number of the elements in the result, used for handling pagination.
     *
     * @return the result of the search as a [List].
     */
    fun searchPrefix(prefix: String, offset: Int = 0, limit: Int = Int.MAX_VALUE): List<T>

    /**
     * Returns all of the elements of the tree as a [List].
     *
     * @return all of the elements resident in the tree.
     */
    fun toList(): List<T>

    /**
     * The current number of the elements in the tree.
     */
    val size: Int
}