package com.aminography.data.remote.user.datasource

import com.aminography.data.remote.user.datasource.model.SearchUsersResponseModel
import com.aminography.domain.base.Result

/**
 * @author aminography
 */
interface UserDataSource {

    suspend fun search(
        location: String
    ): Result<SearchUsersResponseModel>
}