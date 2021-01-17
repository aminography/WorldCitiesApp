package com.aminography.worldcities.ui.citylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aminography.worldcities.ui.base.adapter.BaseViewHolder
import com.aminography.worldcities.ui.base.adapter.OnListItemClickListener

/**
 * @author aminography
 */
class CityListAdapter(
    private val inflater: LayoutInflater
) : PagingDataAdapter<CityItemDataHolder, CityItemViewHolder>(CityComparator) {

    private var onItemClickListener: OnListItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CityItemViewHolder(inflater, parent).also { setupClickListener(it) }

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        holder.dataHolder = getItem(position)
    }

    fun setOnListItemClickListener(listener: OnListItemClickListener?) {
        onItemClickListener = listener
    }

    private fun setupClickListener(viewHolder: BaseViewHolder<*, *>) {
        onItemClickListener?.run {
            viewHolder.itemView.setOnClickListener {
                onItemClicked(getItem(viewHolder.absoluteAdapterPosition))
            }
        }
    }

    private object CityComparator : DiffUtil.ItemCallback<CityItemDataHolder>() {
        override fun areItemsTheSame(
            oldItem: CityItemDataHolder,
            newItem: CityItemDataHolder
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CityItemDataHolder,
            newItem: CityItemDataHolder
        ) = oldItem.city == newItem.city
    }
}


