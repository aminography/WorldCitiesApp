package com.aminography.worldcities.userlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.aminography.worldcities.ui.base.adapter.BaseViewHolder
import com.aminography.worldcities.userlist.databinding.ListItemUserBinding

/**
 * The view-holder class to represent each user data-holder in the [androidx.recyclerview.widget.RecyclerView].
 *
 * @param inflater an instance of [LayoutInflater].
 * @param parent the parent [ViewGroup] to to attach the inflated view to it.
 *
 * @author aminography
 */
class UserItemViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<UserItemDataHolder, ListItemUserBinding>(
    ListItemUserBinding.inflate(inflater, parent, false)
) {

    override fun bindDataToView(dataHolder: UserItemDataHolder) = with(binding) {
        nameTextView.text = dataHolder.user.username
        coordTextView.text = dataHolder.user.avatarUrl
    }
}