package com.aminography.data.remote.user.datasource

import com.aminography.data.remote.base.BaseRemoteDataSource
import com.aminography.data.remote.user.datasource.model.SearchUsersResponseModel
import com.aminography.domain.base.Result
import javax.inject.Inject

/**
 * @author aminography
 */
internal class UserDataSourceImpl @Inject internal constructor(
    private val api: SearchUsersApi
) : BaseRemoteDataSource(), UserDataSource {

    override suspend fun search(
        location: String
    ): Result<SearchUsersResponseModel> = wrapResponse {
        api.search("location:$location", 0, 10)
    }
}
