package com.aminography.data.user.paging

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.domain.base.Result
import com.aminography.model.user.GithubUser

/**
 * The concrete implementation of the [PagingSource] that is used to load pages of data for an
 * instance of [PagingData].
 *
 * @param dataSource the tree of data.
 * @param location the location that should be queried.
 *
 * @author aminography
 */
internal class SearchUserPagingSource(
    private val dataSource: UserDataSource,
    private val location: String
) : PagingSource<Int, GithubUser>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, GithubUser> = try {
        val pageNumber = params.key ?: 1
        val pageSize = params.loadSize

        when (val response = dataSource.search(location, pageNumber, pageSize)) {
            is Result.Success -> {
                val data = response.data!!
                val prevKey = if (pageNumber > 1) pageNumber - 1 else null
                val nextKey = if (data.items.size == pageSize) pageNumber + 1 else null

                LoadResult.Page(
                    data = data.items,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            is Result.Error -> LoadResult.Error(response.cause ?: Exception("Unknown Error!"))
            else -> LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
}