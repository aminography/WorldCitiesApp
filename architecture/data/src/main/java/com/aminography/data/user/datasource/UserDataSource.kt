package com.aminography.data.user.datasource

import com.aminography.data.user.datasource.model.SearchUsersResponseModel
import com.aminography.domain.base.Result

/**
 * @author aminography
 */
interface UserDataSource {

    suspend fun search(
        location: String
    ): Result<SearchUsersResponseModel>
}