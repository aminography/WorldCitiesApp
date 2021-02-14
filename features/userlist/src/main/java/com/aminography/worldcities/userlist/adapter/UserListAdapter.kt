package com.aminography.worldcities.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aminography.worldcities.ui.base.adapter.BaseViewHolder
import com.aminography.worldcities.ui.base.adapter.OnListItemClickListener

/**
 * The adapter class for populating list of users in a [androidx.recyclerview.widget.RecyclerView].
 *
 * @author aminography
 */
class UserListAdapter(
    private val inflater: LayoutInflater
) : PagingDataAdapter<UserItemDataHolder, UserItemViewHolder>(CityComparator) {

    private var onItemClickListener: OnListItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = UserItemViewHolder(inflater, parent).also { setupClickListener(it) }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.dataHolder = getItem(position)
    }

    /**
     * Sets an instance of [OnListItemClickListener].
     *
     * @param listener an instance of [OnListItemClickListener] to be notified when an item of the
     * list is clicked.
     */
    fun setOnListItemClickListener(listener: OnListItemClickListener?) {
        onItemClickListener = listener
    }

    private fun setupClickListener(viewHolder: BaseViewHolder<*, *>) {
        onItemClickListener?.run {
            viewHolder.itemView.setOnClickListener {
                onItemClicked(viewHolder.dataHolder)
            }
        }
    }

    private object CityComparator : DiffUtil.ItemCallback<UserItemDataHolder>() {
        override fun areItemsTheSame(
            oldItem: UserItemDataHolder,
            newItem: UserItemDataHolder
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UserItemDataHolder,
            newItem: UserItemDataHolder
        ) = oldItem.user == newItem.user
    }
}


