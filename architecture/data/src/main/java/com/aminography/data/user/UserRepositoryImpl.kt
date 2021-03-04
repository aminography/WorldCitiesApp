package com.aminography.data.user

import androidx.paging.PagingData
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.data.user.paging.UserPagingFactory
import com.aminography.domain.user.UserRepository
import com.aminography.model.user.GithubUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author aminography
 */
internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val pagingFactory: UserPagingFactory
) : UserRepository {

    override fun search(
        location: String
    ): Flow<PagingData<GithubUser>> = pagingFactory.createPagingDataFlow(userDataSource, location)
}