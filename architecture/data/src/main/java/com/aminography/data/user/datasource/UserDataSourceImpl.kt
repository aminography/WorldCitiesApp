package com.aminography.data.user.datasource

import com.aminography.data.core.remote.BaseRemoteDataSource
import com.aminography.data.user.datasource.model.SearchUsersResponseModel
import javax.inject.Inject

/**
 * @author aminography
 */
internal class UserDataSourceImpl @Inject internal constructor(
    private val api: SearchUsersApi
) : BaseRemoteDataSource(), UserDataSource {

    override suspend fun search(
        location: String,
        page: Int,
        pageSize: Int
    ): Result<SearchUsersResponseModel> = wrapResponse {
        api.search("location:$location", page, pageSize)
    }
}
