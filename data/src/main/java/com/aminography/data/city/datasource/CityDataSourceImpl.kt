package com.aminography.data.city.datasource

import com.aminography.data.city.datasource.adapter.MutableListAdapter
import com.aminography.data.city.datasource.adapter.RadixTreeAdapter
import com.aminography.data.city.datasource.reader.JsonRetriever
import com.aminography.data.city.datasource.reader.LineCounter
import com.aminography.domain.city.ds.MinimalRadixTree
import com.aminography.domain.city.ds.RadixTree
import com.aminography.domain.city.util.key
import com.aminography.model.city.City
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * The concrete implementation of the [CityDataSource].
 *
 * @param jsonRetriever an instance of [JsonRetriever] that helps to read the file of cities.
 * @param lineCounter an instance of [LineCounter] that helps to number of lines in a text file.
 * @param fileName the name of the cities file, located in `assets` directory.
 * @param concurrencyLevel the number of concurrent coroutines to read the file.
 * @param defaultDispatcher the [CoroutineDispatcher] for executing blocking IO tasks.
 * @param ioDispatcher the [CoroutineDispatcher] for executing CPU-bounded tasks.
 *
 * @author aminography
 */
internal class CityDataSourceImpl @Inject constructor(
    private val jsonRetriever: JsonRetriever,
    private val lineCounter: LineCounter,
    private val fileName: String,
    private val concurrencyLevel: Int,
    private val defaultDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) : CityDataSource {

    override suspend fun loadCityList(): List<City> =
        arrayListOf<City>().apply {
            jsonRetriever.readTo(fileName, MutableListAdapter(this))
        }

    override suspend fun loadCityRadixTree(): RadixTree<City> =
        MinimalRadixTree<City>().apply {
            jsonRetriever.readTo(fileName, RadixTreeAdapter(this) { it.key })
        }

    override suspend fun loadCityListConcurrently(): List<City> {
        val resultList = mutableListOf<List<City>>()

        coroutineScope {
            val counter = async(ioDispatcher) {
                lineCounter.count(fileName)
            }

            val totalCount = counter.await()
            val chunkSize = totalCount / concurrencyLevel
            val chunkList = mutableListOf<ChunkDataContext>()

            repeat(concurrencyLevel) {
                val start = it * chunkSize + if (it == 0) 0 else 1
                val end = if (it == concurrencyLevel - 1) totalCount - 1 else (it + 1) * chunkSize
                chunkList.add(ChunkDataContext(start, end))
            }

            val deferredList = mutableListOf<Deferred<List<City>>>()
            repeat(concurrencyLevel) {
                val deferred = async(ioDispatcher + chunkList[it]) {
                    arrayListOf<City>().also {
                        coroutineContext[ChunkDataContext]?.run {
                            val offset = start
                            val limit = end - start + 1
                            jsonRetriever.readTo(fileName, MutableListAdapter(it), offset, limit)
                        }
                    }.let {
                        withContext(defaultDispatcher) {
                            it.sortedBy { it.name }
                        }
                    }
                }
                deferredList.add(deferred)
            }

            deferredList.forEach { resultList.add(it.await()) }
        }

        return withContext(defaultDispatcher) {
            // should be replaced by merging n sorted list algorithm
            resultList.flatten().sortedBy { it.name }
        }
    }

    private data class ChunkDataContext(
        val start: Int,
        val end: Int
    ) : AbstractCoroutineContextElement(ChunkDataContext) {

        companion object Key : CoroutineContext.Key<ChunkDataContext>

        override fun toString(): String = "ChunkDataContext[start: $start, end: $end)"
    }
}



