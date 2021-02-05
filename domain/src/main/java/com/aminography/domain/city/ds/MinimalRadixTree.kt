package com.aminography.domain.city.ds

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * A concrete implementation for [RadixTree].
 * Overall, `RadixTree` or compressed trie is the compact and space-optimized form of prefix tree
 * which enables us to find all nodes starting with a prefix string by a `O(L + V)` complexity order,
 * where `L` is the length of input prefix, and `V` stands for number of nodes containing the desired
 * value. In case of this project dataset, the length of keys for the cities are dramatically lower
 * than number of cities, which means that the time complexity of this search is significantly
 * better than linear search.
 *
 * @param T the type of the elements contained in the tree.
 *
 * @author aminography
 */
class MinimalRadixTree<T> : RadixTree<T> {

    // the root node of the tree
    private var root: TreeNode<T> = TreeNode()

    override var size: Int = 0
        private set

    override fun insert(key: String, value: T, replace: Boolean) {
        size++
        insert(key, value, replace, root)
    }

    /**
     * Inserts the input element into the tree at the right place according to the corresponding key.
     * As far as the maximum depth of the tree equals to the maximum length of the input keys, and
     * the length of keys is limited in our dataset, calling the recursive insertion will not cause
     * a stack overflow exception. The time complexity to insert a node is `O(l)` where `l` is the
     * length of the key associated with the value. So, the overall complexity to inserting `n` pairs
     * of key/value is `O(L * n)` where `L` stands for the maximum length of keys in the dataset.
     */
    private fun insert(key: String, value: T, replace: Boolean, node: TreeNode<T>) {
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
                return
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
    }

    override fun toList(): List<T> = searchPrefix("")

    /*
     * Explores the result of the prefix search in two steps:
     * 1. Finds the first node whose key satisfies the prefix string exactly.
     * 2. Explores children of the node that is found in step 1.
     */
    override fun searchPrefix(prefix: String, offset: Int, limit: Int): List<T> {
        if (offset >= size || limit <= 0) return listOf()
        val tunedOffset = if (offset < 0) 0 else offset

        return findPrefixRoot(prefix)?.let {
            exploreChildrenValuesViaDFS(it, tunedOffset, limit)
        } ?: listOf()
    }

    /**
     * Explores the tree and finds the first node whose path from the root matches all the prefix
     * characters in order. To find the target node, the tree will be traversed by a heuristic
     * Breadth-First Search (BFS) algorithm. So that at each level of the tree, the algorithm chooses
     * the next step by finding the only child whose key will match the rest of the input prefix.
     * So, the time complexity for this function is `O(L)` where `L` is the length of the input prefix.
     */
    private fun findPrefixRoot(prefix: String): TreeNode<T>? {
        var result: TreeNode<T>? = null
        val queue = LinkedList<TreeNode<T>>()
        queue.add(root)
        var keyDiff = prefix
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

    /**
     * Explores all of the nodes in the subtree of [root] by using a Depth-First Search (DFS)
     * algorithm. The traversal is in such a way that the order of inserting elements gets retained
     * in the resulting list. To analyze the time complexity for this function, consider `V` as the
     * number of nodes containing a value. The maximum number of nodes should be traversed to
     * explore them is `(2V - 1)`. So, we can say that the time complexity is `O(V)`.
     */
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

    /**
     * Calculates the longest common length between two strings starting from their zero indices.
     */
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
    @Suppress("unused")
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