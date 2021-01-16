package com.aminography.worldcities.ui.base.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * A class contains base functionality of a [RecyclerView.Adapter] to make adapter classes cleaner.
 * This implementation is based on [AsyncListDiffer] which makes data changes presentation more efficient.
 *
 * @author aminography
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder<BaseDataHolder, ViewBinding>>() {

    protected open val diffUtilCallback: DiffUtil.ItemCallback<BaseDataHolder> =
        DEFAULT_DIFF_UTIL_CALLBACK

    protected val listDiffer: AsyncListDiffer<BaseDataHolder> by lazy {
        AsyncListDiffer(this, diffUtilCallback)
    }

    protected var onItemClickListener: OnListItemClickListener? = null
    protected var onItemLongClickListener: OnListItemLongClickListener? = null

    private lateinit var inflater: LayoutInflater

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseDataHolder, ViewBinding> {
        setupInflater(parent)
        return createViewHolder(inflater, parent, viewType).also {
            setupClickListener(it)
        } as BaseViewHolder<BaseDataHolder, ViewBinding>
    }

    protected abstract fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*, *>

    override fun onBindViewHolder(
        viewHolder: BaseViewHolder<BaseDataHolder, ViewBinding>,
        position: Int
    ) {
        if (position in 0 until itemCount) {
            viewHolder.dataHolder = itemAt(position)
        }
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun itemAt(position: Int): BaseDataHolder = listDiffer.currentList[position]

    fun submitList(list: List<BaseDataHolder>) {
        listDiffer.submitList(list)
    }

    fun setOnListItemClickListener(listener: OnListItemClickListener?) {
        onItemClickListener = listener
    }

    fun setOnListItemLongClickListener(listener: OnListItemLongClickListener?) {
        onItemLongClickListener = listener
    }

    private fun setupInflater(parent: ViewGroup) {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }
    }

    protected open fun setupClickListener(viewHolder: BaseViewHolder<*, *>) {
        onItemClickListener?.run {
            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.adapterPosition
                if (position in 0 until itemCount) {
                    onItemClicked(itemAt(position))
                }
            }
        }
        onItemLongClickListener?.run {
            viewHolder.itemView.setOnLongClickListener {
                val position = viewHolder.adapterPosition
                return@setOnLongClickListener if (position in 0 until itemCount) {
                    onItemLongClicked(itemAt(position))
                } else false
            }
        }
    }

    companion object {
        private val DEFAULT_DIFF_UTIL_CALLBACK: DiffUtil.ItemCallback<BaseDataHolder> =
            object : DiffUtil.ItemCallback<BaseDataHolder>() {
                override fun areItemsTheSame(new: BaseDataHolder, old: BaseDataHolder): Boolean {
                    return new.id == old.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(new: BaseDataHolder, old: BaseDataHolder): Boolean {
                    return new == old
                }
            }
    }
}