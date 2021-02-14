package com.aminography.domain.user

import androidx.paging.PagingData
import com.aminography.model.user.GithubUser
import kotlinx.coroutines.flow.Flow

/**
 * @author aminography
 */
interface UserRepository {

    fun search(
        location: String
    ): Flow<PagingData<GithubUser>>
}