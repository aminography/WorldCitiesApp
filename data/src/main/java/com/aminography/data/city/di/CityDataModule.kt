package com.aminography.data.city.di

import android.content.Context
import androidx.paging.PagingConfig
import com.aminography.data.KEY_FILE_NAME
import com.aminography.data.KEY_INITIAL_LOAD_SIZE
import com.aminography.data.KEY_PAGE_SIZE
import com.aminography.data.city.CityRepositoryImpl
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.datasource.CityDataSourceImpl
import com.aminography.data.city.datasource.JsonRetriever
import com.aminography.data.city.paging.PagingFactory
import com.aminography.domain.city.CityRepository
import com.aminography.model.city.City
import com.aminography.scope.CityListScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author aminography
 */
@Module
class CityDataModule {

    @Provides
    @Named(KEY_FILE_NAME)
    internal fun providesFileName(): String = "cities.json"

    @Provides
    @Named(KEY_PAGE_SIZE)
    internal fun providesPageSize(): Int = 20

    @Provides
    @Named(KEY_INITIAL_LOAD_SIZE)
    internal fun providesInitialLoadSize(): Int = 30

    @CityListScope
    @Provides
    internal fun providesPagingConfig(
        @Named(KEY_PAGE_SIZE) pageSize: Int,
        @Named(KEY_INITIAL_LOAD_SIZE) initialLoadSize: Int
    ): PagingConfig = PagingConfig(pageSize = pageSize, initialLoadSize = initialLoadSize)

    @CityListScope
    @Provides
    internal fun providesGson(): Gson = GsonBuilder().create()

    @CityListScope
    @Provides
    internal fun providesJsonRetriever(
        context: Context,
        gson: Gson
    ): JsonRetriever = JsonRetriever(context, gson)

    @CityListScope
    @Provides
    internal fun providesCityDataSource(
        jsonRetriever: JsonRetriever,
        @Named(KEY_FILE_NAME) fileName: String
    ): CityDataSource = CityDataSourceImpl(jsonRetriever, fileName)

    @CityListScope
    @Provides
    internal fun providesPagerFactory(
        pagingConfig: PagingConfig
    ): PagingFactory<City> = PagingFactory(pagingConfig)

    @CityListScope
    @Provides
    internal fun providesCityRepository(
        cityDataSource: CityDataSource,
        pagingFactory: PagingFactory<City>
    ): CityRepository = CityRepositoryImpl(cityDataSource, pagingFactory)
}