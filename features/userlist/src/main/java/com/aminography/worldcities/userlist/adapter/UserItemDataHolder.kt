package com.aminography.worldcities.userlist.adapter

import com.aminography.model.user.GithubUser
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder

/**
 * The data-holder entity corresponding to each [GithubUser] instance for feeding [UserListAdapter].
 *
 * @param user an instance of [GithubUser] entity.
 *
 * @author aminography
 */
data class UserItemDataHolder(
    val user: GithubUser
) : BaseDataHolder() {

    override val id: Int
        get() = user.id
}