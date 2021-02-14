package com.aminography.worldcities.userlist.model

import com.aminography.model.user.GithubUser
import com.aminography.worldcities.userlist.adapter.UserItemDataHolder

/**
 * A mapper extension function that maps an instance of [GithubUser] to an instance of [UserItemDataHolder].
 *
 * @return an instance of [UserItemDataHolder] corresponding to the [GithubUser].
 *
 * @author aminography
 */
fun GithubUser.toUserItemDataHolder() =
    UserItemDataHolder(this)
