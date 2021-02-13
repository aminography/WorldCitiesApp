package com.aminography.domain.city.ds

/**
 * Models internal node for the [RadixTree] to build the hierarchy of the nodes. Each node can
 * contain a value, or be just a transitional node.
 *
 * @param T the type of the elements contained in the tree.
 * @param key the key of the node in the tree hierarchy.
 * @param value the possible element resident in this node.
 * @param children the list of children for the current node.
 *
 * @author aminography
 */
internal class TreeNode<T>(
    var key: String = "",
    var value: T? = null,
    var children: List<TreeNode<T>>? = null
) {

    /**
     * Returns true if there exists at least one child for the current node.
     */
    val hasChildren: Boolean
        get() = children?.isNotEmpty() ?: false

    /**
     * Adds a child to the current node.
     *
     * @param child the node to be added as a child.
     */
    fun addChild(child: TreeNode<T>) {
        if (children == null) children = ArrayList()
        (children as MutableList).add(child)
    }

    /**
     * Considers a new list of children for the current node.
     */
    fun resetChildren() {
        // to keep memory consumption as low as possible
        if (children == null) return
        children = ArrayList()
    }

    override fun toString(): String = key
}