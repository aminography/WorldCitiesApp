package com.aminography.domain.city.ds

import java.util.*
import kotlin.collections.ArrayList

/**
 * @author aminography
 */
class MinimalRadixTree<T> : RadixTree<T> {

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

    override fun searchPrefix(prefix: String): ArrayList<T> {
        val result = ArrayList<T>()
        findPrefixRoot(prefix)?.run {
            value?.let { result.add(it) }
            exploreChildrenValuesViaBFS(this, result)
        }
        return result
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
                node.children?.forEach {
                    if (it.key.startsWith(keyDiff[0])) {
                        queue.add(it)
                        return@forEach
                    }
                }
            } else if (lcs == keyDiff.length) {
                result = node
                break
            }
        }
        return result
    }

    private fun exploreChildrenValuesViaBFS(root: TreeNode<T>, result: MutableList<T>) {
        val queue = LinkedList<TreeNode<T>>()
        root.children?.let { queue.addAll(it) }
        while (queue.isNotEmpty()) {
            queue.remove().run {
                value?.let { result.add(it) }
                children?.let { queue.addAll(it) }
            }
        }
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
    fun visualize() {
        if (!root.hasChildren) {
            println("<empty>")
            return
        }

        fun traverse(node: TreeNode<T>?, prefix: String) {
            if (node?.hasChildren == false) {
                println("╴ ${node.key}")
                return
            }
            println("┐ ${node?.key}")
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