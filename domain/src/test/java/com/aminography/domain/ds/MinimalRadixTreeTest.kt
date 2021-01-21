package com.aminography.domain.ds

import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import com.aminography.test.testCityNames
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
        val expected = testCityNames.sorted()

        val tree: RadixTree<String> = MinimalRadixTree()

        // When
        expected.forEach { tree.insert(it, it) }
        val result = tree.toList()

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `should preserve keys sorted while inserting sorted items in searching subtree`() {
        // Given
        val prefix = "Ho"
        val all = testCityNames.sorted()
        val expected = all.filter { it.startsWith(prefix) }

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(prefix)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `find nothing when the prefix does not exists`() {
        // Given
        val prefix = "HHH"
        val all = testCityNames.sorted()
        val expected = all.filter { it.startsWith(prefix) }

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(prefix)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `should preserve keys sorted in case of fetching with offset and limit`() {
        // Given
        val offset = 5
        val limit = 10
        val query = ""
        val all = testCityNames.sorted()
        val expected = all.filter { it.startsWith(query) }.subList(offset, offset + limit)

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, offset, limit)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `should preserve keys sorted in case of fetching with offset and limit in subtree`() {
        // Given
        val offset = 2
        val limit = 4
        val query = "Ho"
        val all = testCityNames.sorted()
        val expected = all.filter { it.startsWith(query) }.subList(offset, offset + limit)

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, offset, limit)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `search should react to negative offset values like zero`() {
        // Given
        val offset = -2
        val query = ""
        val all = testCityNames

        val tree1: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree1.insert(it, it) }
        val expected = tree1.searchPrefix(query, 0)

        val tree2: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree2.insert(it, it) }

        // When
        val result = tree2.searchPrefix(query, offset = offset)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `search should return empty for offset values equal to tree size`() {
        // Given
        val offset = testCityNames.size + 1
        val query = ""
        val all = testCityNames
        val expected = listOf<String>()

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, offset = offset)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `search should return empty for offset values greater than tree size`() {
        // Given
        val offset = testCityNames.size
        val query = ""
        val all = testCityNames
        val expected = listOf<String>()

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, offset = offset)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `search should return empty for limit values equal to zero`() {
        // Given
        val limit = 0
        val query = ""
        val all = testCityNames
        val expected = listOf<String>()

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, limit = limit)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `search should return empty for negative limit values`() {
        // Given
        val limit = -1
        val query = ""
        val all = testCityNames
        val expected = listOf<String>()

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, limit = limit)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `search should react to limit value greater than size like size value`() {
        // Given
        val limit = testCityNames.size + 1
        val query = ""
        val all = testCityNames

        val tree1: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree1.insert(it, it) }
        val expected = tree1.searchPrefix(query, limit = testCityNames.size)

        val tree2: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree2.insert(it, it) }

        // When
        val result = tree2.searchPrefix(query, limit = limit)

        // Then
        assertEquals(result, expected)
    }

    @Test
    fun `limit should not affect result when it is greater than result size`() {
        // Given
        val limit = testCityNames.size + 1
        val query = "Ho"
        val all = testCityNames.sorted()
        val expected = all.filter { it.startsWith(query) }

        val tree: RadixTree<String> = MinimalRadixTree()
        all.forEach { tree.insert(it, it) }

        // When
        val result = tree.searchPrefix(query, limit = limit)

        // Then
        assertEquals(result, expected)
    }
}