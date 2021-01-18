package com.aminography.domain.city.ds

import androidx.annotation.VisibleForTesting
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * @author aminography
 */
class MinimalRadixTree<T>() : RadixTree<T> {

    private var root: TreeNode<T> = TreeNode()

    override var size: Int = 0
        private set

    override fun insert(key: String, value: T, replace: Boolean) {
        size++
        insert(key, value, replace, root)
    }

    private fun insert(key: String, value: T, replace: Boolean, node: TreeNode<T>): Boolean {
        val lcs = longestCommonPrefix(node.key, key)
        val keyDiff = key.substring(lcs)

        if (lcs == 0 || (lcs < key.length && lcs == node.key.length)) {
            var found = false
            node.children?.let { children ->
                for (child in children) {
                    if (child.key.startsWith(keyDiff[0])) {
                        found = true
                        insert(keyDiff, value, replace, child)
                        break
                    }
                }
            }

            if (!found) {
                node.addChild(TreeNode(keyDiff, value))
            }
        } else if (lcs > 0 && lcs < node.key.length) {
            val child = TreeNode(
                node.key.substring(lcs),
                node.value,
                node.children
            )

            node.key = key.substring(0, lcs)
            node.resetChildren()
            node.addChild(child)

            if (lcs < key.length) {
                node.value = null
                node.addChild(TreeNode(keyDiff, value))
            } else {
                node.value = value
            }
        } else if (lcs == key.length && lcs == node.key.length) {
            if (node.value != null) {
                if (replace) node.value = value
                size--
                return false
            }
            node.value = value
        } else {
            val child = TreeNode(
                node.key.substring(lcs),
                node.value,
                node.children
            )

            node.key = key
            node.value = value
            node.addChild(child)
        }
        return true
    }

    override fun toList(): List<T> = searchPrefix("")

    override fun searchPrefix(prefix: String, offset: Int, limit: Int): List<T> {
        if (offset >= size || limit <= 0) return listOf()
        val tunedOffset = if (offset < 0) 0 else offset

        return findPrefixRoot(prefix)?.let {
            exploreChildrenValuesViaDFS(it, tunedOffset, limit)
        } ?: listOf()
    }

    private fun findPrefixRoot(key: String): TreeNode<T>? {
        var result: TreeNode<T>? = null
        val queue = LinkedList<TreeNode<T>>()
        queue.add(root)
        var keyDiff = key
        while (queue.isNotEmpty()) {
            val node = queue.remove()
            val lcs = longestCommonPrefix(node.key, keyDiff)
            if (lcs < keyDiff.length && lcs == node.key.length) {
                keyDiff = keyDiff.substring(lcs)
                node.children?.let { children ->
                    for (child in children) {
                        if (child.key.startsWith(keyDiff[0])) {
                            queue.add(child)
                            break
                        }
                    }
                }
            } else if (lcs == keyDiff.length) {
                result = node
                break
            }
        }
        return result
    }

    private fun exploreChildrenValuesViaDFS(
        root: TreeNode<T>,
        offset: Int,
        limit: Int
    ): ArrayList<T> {
        var skipped = 0
        val stack = Stack<TreeNode<T>>()
        val visited = HashSet<TreeNode<T>>()
        val result = ArrayList<T>()

        stack.push(root)
        while (stack.isNotEmpty() && result.size < limit) {
            val node = stack.pop()
            if (!visited.contains(node)) {
                visited.add(node)

                node.value?.let {
                    if (skipped < offset) skipped++
                    else result.add(it)
                }
                node.children?.let {
                    for (i in it.size - 1 downTo 0) stack.push(it[i])
                }
            }
        }
        stack.clear()
        return result
    }

    private fun longestCommonPrefix(first: String, second: String): Int {
        val max = first.length.coerceAtMost(second.length)
        for (i in 0 until max) {
            if (first[i] != second[i]) return i
        }
        return max
    }

    /**
     * Visualizes tree structure in console.
     * Notice that should not be used for printing large trees.
     */
    @VisibleForTesting
    internal fun visualize() {
        if (!root.hasChildren) {
            println("<empty>")
            return
        }

        fun traverse(node: TreeNode<T>?, prefix: String) {
            if (node?.hasChildren == false) {
                println("╴ ${node.key}" + (node.value?.let { " [$it]" } ?: ""))
                return
            }
            println("┐ ${node?.key}" + (node?.value?.let { " [$it]" } ?: ""))
            for (child in node?.children?.dropLast(1) ?: listOf()) {
                print("$prefix├─")
                traverse(child, "$prefix│ ")
            }
            print("$prefix└─")
            traverse(node?.children?.last(), "$prefix  ")
        }

        traverse(root, "")
    }
}