package com.aminography.data.remote.user

import com.aminography.data.remote.user.datasource.UserDataSource
import com.aminography.domain.base.Result
import com.aminography.domain.base.map
import com.aminography.domain.user.UserRepository
import com.aminography.model.user.GithubUser
import javax.inject.Inject

/**
 * @author aminography
 */
internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun search(
        location: String
    ): Result<List<GithubUser>> = userDataSource.search(location).map { it.items }
}