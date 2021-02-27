package com.aminography.data.user.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.model.user.GithubUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A factory class responsible for creating a [Flow] of [PagingData], according to a specific
 * [PagingConfig].
 *
 * @param pagingConfig the configuration parameters for pagination.
 *
 * @author aminography
 */
internal class UserPagingFactory @Inject constructor(
    private val pagingConfig: PagingConfig
) {

    /**
     * The factory method that creates a [Flow] of [PagingData] in searching the [location] on the [dataSource].
     *
     * @param dataSource an instance of [UserDataSource].
     * @param location the location that should be queried.
     */
    fun createPagingDataFlow(
        dataSource: UserDataSource,
        location: String
    ): Flow<PagingData<GithubUser>> =
        Pager(pagingConfig) { SearchUserPagingSource(dataSource, location) }.flow
}