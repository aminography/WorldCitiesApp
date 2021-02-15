package com.aminography.data.user

import androidx.paging.PagingData
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.domain.base.Result
import com.aminography.domain.base.map
import com.aminography.domain.user.UserRepository
import com.aminography.model.user.GithubUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author aminography
 */
internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun search(
        location: String
    ): Flow<PagingData<GithubUser>> = flow {
        // TODO: This is for test, so errors are not transmitted.
        userDataSource.search(location).map { it.items }.let {
            when (it) {
                is Result.Success -> emit(PagingData.from(it.data.orEmpty()))
                else -> emit(PagingData.empty<GithubUser>())
            }
        }
    }
}