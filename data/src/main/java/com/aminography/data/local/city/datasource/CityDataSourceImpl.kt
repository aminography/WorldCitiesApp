package com.aminography.data.local.city.datasource

import com.aminography.data.local.city.datasource.adapter.MutableListAdapter
import com.aminography.data.local.city.datasource.adapter.RadixTreeAdapter
import com.aminography.data.local.city.datasource.reader.JsonRetriever
import com.aminography.data.local.city.datasource.reader.LineCounter
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
            val chunkContextList = mutableListOf<ChunkContext>()

            repeat(concurrencyLevel) {
                val start = it * chunkSize + if (it == 0) 0 else 1
                val end = if (it == concurrencyLevel - 1) totalCount - 1 else (it + 1) * chunkSize
                chunkContextList.add(ChunkContext(start, end))
            }

            val deferredList = mutableListOf<Deferred<List<City>>>()
            chunkContextList.forEach {
                val deferred = async(ioDispatcher + it) {
                    arrayListOf<City>().also {
                        coroutineContext[ChunkContext]?.run {
                            val offset = start
                            val limit = end - start + 1
                            jsonRetriever.readTo(fileName, MutableListAdapter(it), offset, limit)
                        }
                    }.let {
                        withContext(defaultDispatcher) {
                            it.sortedBy { city -> city.name }
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

    private data class ChunkContext(
        val start: Int,
        val end: Int
    ) : AbstractCoroutineContextElement(ChunkContext) {

        companion object Key : CoroutineContext.Key<ChunkContext>

        override fun toString(): String = "ChunkContext[start: $start, end: $end)"
    }
}



