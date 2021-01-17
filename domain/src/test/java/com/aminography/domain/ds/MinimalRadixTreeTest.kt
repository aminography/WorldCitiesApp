package com.aminography.domain.ds

import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimalRadixTreeTest {

    @Test
    fun `size should increase by insertion`() {
        // Given
        val tree: RadixTree<Int> = MinimalRadixTree()

        // When
        tree.insert("1", 1)

        // Then
        assertEquals(tree.size, 1)
    }

    @Test
    fun `element with existing key should not be inserted`() {
        // Given
        val expected = listOf(1)
        val tree: RadixTree<Int> = MinimalRadixTree()

        // When
        tree.insert("1", 1)
        tree.insert("1", 2)
        val result = tree.toList()

        // Then
        assertEquals(tree.size, 1)
        assertEquals(result, expected)
    }

    @Test
    fun `element with existing key should replace the old one due to replace flag`() {
        // Given
        val expected = listOf(2)
        val tree: RadixTree<Int> = MinimalRadixTree()

        // When
        tree.insert("1", 1)
        tree.insert("1", 2, true)
        val result = tree.toList()

        // Then
        assertEquals(tree.size, 1)
        assertEquals(result, expected)
    }

    @Test
    fun `should preserve keys sorted while inserting sorted items`() {
        // Given
        val expected = listOf("A", "AB", "ABC", "AC", "B", "CA", "CAD", "CB", "CBC")
        val tree: RadixTree<String> = MinimalRadixTree()

        // When
        expected.forEach { tree.insert(it, it) }
        val result = tree.toList()

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `should preserve keys sorted while inserting sorted items in subtree`() {
        // Given
        val all = listOf("A", "AB", "ABC", "AC", "B", "CA", "CAD", "CB", "CBC")
        val expected = listOf("CA", "CAD", "CB", "CBC")
        val tree: RadixTree<String> = MinimalRadixTree()

        // When
        all.forEach { tree.insert(it, it) }
        val result = tree.searchPrefix("C")

        // Then
        assertEquals(result, expected)
    }
}