package com.aminography.domain.city.ds

/**
 * @author aminography
 */
internal class TreeNode<T>(
    var key: String = "",
    var value: T? = null,
    var children: List<TreeNode<T>>? = null
) {

    val hasChildren: Boolean
        get() = children?.isNotEmpty() ?: false

    fun addChild(child: TreeNode<T>) {
        if (children == null) children = ArrayList()
        (children as MutableList).add(child)
    }

    fun resetChildren() {
        // to keep memory consumption as low as possible
        if (children == null) return
        children = ArrayList()
    }

    override fun toString(): String = key
}