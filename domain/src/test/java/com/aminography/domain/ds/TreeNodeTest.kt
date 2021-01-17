package com.aminography.domain.ds

import com.aminography.domain.city.ds.TreeNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TreeNodeTest {

    @Test
    fun `hasChildren should return false if no children has been added`() {
        // Given
        val node: TreeNode<Unit> = TreeNode()

        // Then
        assertEquals(node.hasChildren, false)
    }

    @Test
    fun `children should be null initially to reduce memory usage`() {
        // Given
        val node: TreeNode<Unit> = TreeNode()

        // Then
        assertEquals(node.children, null)
    }

    @Test
    fun `hasChildren should return true if at least one child exists`() {
        // Given
        val node: TreeNode<Unit> = TreeNode()
        val child: TreeNode<Unit> = TreeNode()

        // When
        node.addChild(child)

        // Then
        assertEquals(node.hasChildren, true)
    }

    @Test
    fun `allocate children if it is null when adding a child`() {
        // Given
        val node: TreeNode<Unit> = TreeNode()
        val child: TreeNode<Unit> = TreeNode()

        // When
        node.addChild(child)

        // Then
        assertEquals(node.children?.size, 1)
    }

    @Test
    fun `resetChildren should left children null if it is null`() {
        // Given
        val node: TreeNode<Unit> = TreeNode()

        // When
        node.resetChildren()

        // Then
        assertEquals(node.children, null)
    }

    @Test
    fun `resetChildren should assign a new list to children if it is not null`() {
        // Given
        val node: TreeNode<Unit> = TreeNode()
        val child: TreeNode<Unit> = TreeNode()

        // When
        node.addChild(child)
        val beforeReset = node.children
        node.resetChildren()

        // Then
        assertNotEquals(node.children, null)
        assertNotEquals(node.children, beforeReset)
    }
}