package com.aminography.data.city.paging

import androidx.paging.PagingSource
import com.aminography.domain.city.ds.RadixTree

/**
 * @author aminography
 */
internal class SearchTreePagingSource<T : Any>(
    private val radixTree: RadixTree<T>,
    private val query: String
) : PagingSource<Int, T>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, T> = try {
        val pageNumber = params.key ?: 0

        val offset = pageNumber * params.loadSize
        val limit = params.loadSize

        val response = radixTree.searchPrefix(query, offset, limit)

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
}