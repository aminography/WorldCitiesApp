package com.aminography.domain.user

import com.aminography.domain.base.Result
import com.aminography.model.user.GithubUser

/**
 * @author aminography
 */
interface UserRepository {

    suspend fun search(
        location: String
    ): Result<List<GithubUser>>
}