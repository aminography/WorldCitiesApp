package com.aminography.data.city.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aminography.domain.city.ds.RadixTree
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author aminography
 */
internal class PagingFactory<T : Any> @Inject constructor(
    private val pagingConfig: PagingConfig
) {

    fun createPagingDataFlow(tree: RadixTree<T>, query: String): Flow<PagingData<T>> =
        Pager(pagingConfig) { SearchTreePagingSource(tree, query) }.flow
}