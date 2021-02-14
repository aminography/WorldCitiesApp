package com.aminography.data.city.di

import android.content.Context
import androidx.paging.PagingConfig
import com.aminography.coroutine.di.DefaultDispatcher
import com.aminography.coroutine.di.IoDispatcher
import com.aminography.data.KEY_CONCURRENCY_LEVEL
import com.aminography.data.KEY_FILE_NAME
import com.aminography.data.KEY_INITIAL_LOAD_SIZE
import com.aminography.data.KEY_PAGE_SIZE
import com.aminography.data.city.CityRepositoryImpl
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.datasource.CityDataSourceImpl
import com.aminography.data.city.datasource.reader.JsonRetriever
import com.aminography.data.city.datasource.reader.LineCounter
import com.aminography.data.city.paging.PagingFactory
import com.aminography.domain.city.CityRepository
import com.aminography.model.city.City
import com.aminography.scope.annotation.FeatureScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

/**
 * A dagger module class defining how to provide city related dependencies for injection.
 *
 * @author aminography
 */
@Module
class CityDataModule {

    @FeatureScope
    @Provides
    @Named(KEY_FILE_NAME)
    internal fun providesFileName(): String = "cities.json"

    @FeatureScope
    @Provides
    @Named(KEY_CONCURRENCY_LEVEL)
    internal fun providesConcurrencyLevel(): Int = 8

    @FeatureScope
    @Provides
    @Named(KEY_PAGE_SIZE)
    internal fun providesPageSize(): Int = 40

    @FeatureScope
    @Provides
    @Named(KEY_INITIAL_LOAD_SIZE)
    internal fun providesInitialLoadSize(): Int = 80

    @FeatureScope
    @Provides
    internal fun providesPagingConfig(
        @Named(KEY_PAGE_SIZE) pageSize: Int,
        @Named(KEY_INITIAL_LOAD_SIZE) initialLoadSize: Int
    ): PagingConfig = PagingConfig(pageSize = pageSize, initialLoadSize = initialLoadSize)

    @FeatureScope
    @Provides
    internal fun providesJsonRetriever(
        context: Context,
        gson: Gson
    ): JsonRetriever = JsonRetriever(context, gson)

    @FeatureScope
    @Provides
    internal fun providesLineCounter(
        context: Context
    ): LineCounter = LineCounter(context)

    @FeatureScope
    @Provides
    internal fun providesCityDataSource(
        jsonRetriever: JsonRetriever,
        lineCounter: LineCounter,
        @Named(KEY_FILE_NAME) fileName: String,
        @Named(KEY_CONCURRENCY_LEVEL) concurrencyLevel: Int,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CityDataSource = CityDataSourceImpl(
        jsonRetriever,
        lineCounter,
        fileName,
        concurrencyLevel,
        defaultDispatcher,
        ioDispatcher
    )

    @FeatureScope
    @Provides
    internal fun providesPagerFactory(
        pagingConfig: PagingConfig
    ): PagingFactory<City> = PagingFactory(pagingConfig)

    @FeatureScope
    @Provides
    internal fun providesCityRepository(
        cityDataSource: CityDataSource,
        pagingFactory: PagingFactory<City>
    ): CityRepository = CityRepositoryImpl(cityDataSource, pagingFactory)
}