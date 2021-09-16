package com.aminography.data.user.datasource

import com.aminography.data.user.datasource.model.SearchUsersResponseModel

/**
 * @author aminography
 */
interface UserDataSource {

    suspend fun search(
        location: String,
        page: Int,
        pageSize: Int
    ): Result<SearchUsersResponseModel>
}
