package com.aminography.data.city.paging

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aminography.model.city.City
import com.aminography.radixtree.RadixTree

/**
 * The concrete implementation of the [PagingSource] that is used to load pages of data for an
 * instance of [PagingData].
 *
 * @param tree the tree of data.
 * @param query the query that should be used for the prefix search on the tree.
 *
 * @author aminography
 */
internal class SearchCityPagingSource(
    private val tree: RadixTree<City>,
    private val query: String
) : PagingSource<Int, City>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, City> = try {
        val pageNumber = params.key ?: 0

        val offset = pageNumber * params.loadSize
        val limit = params.loadSize

        val response = tree.prefixSearch(query, offset, limit)

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null

        LoadResult.Page(
            data = response,
            prevKey = prevKey,
            nextKey = nextKey
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, City>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
}