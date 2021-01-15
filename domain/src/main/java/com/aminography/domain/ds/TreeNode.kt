package com.aminography.domain.ds

/**
 * @author aminography
 */
internal class TreeNode<T>(
    var key: String = "",
    var value: T? = null,
    var children: List<TreeNode<T>>? = null
) {

    fun resetChildren() {
        if (children == null) return
        children = ArrayList()
    }

    fun addChild(child: TreeNode<T>) {
        if (children == null) children = ArrayList()
        (children as MutableList).add(child)
    }

    val hasChildren: Boolean
        get() = children?.isNotEmpty() ?: false

    override fun toString(): String = key
}