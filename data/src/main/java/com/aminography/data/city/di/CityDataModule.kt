package com.aminography.data.city.di

import android.content.Context
import com.aminography.data.KEY_FILE_NAME
import com.aminography.data.KEY_INITIAL_LOAD_SIZE
import com.aminography.data.KEY_PAGE_SIZE
import com.aminography.data.city.CityRepositoryImpl
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.datasource.CityDataSourceImpl
import com.aminography.domain.city.CityRepository
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
    fun providesFileName(): String = "cities.json"

    @Provides
    @Named(KEY_PAGE_SIZE)
    fun providesPageSize(): Int = 20

    @Provides
    @Named(KEY_INITIAL_LOAD_SIZE)
    fun providesInitialLoadSize(): Int = 30

    @Provides
    @CityListScope
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @CityListScope
    fun providesCityDataSource(
        context: Context,
        gson: Gson,
        @Named(KEY_FILE_NAME) fileName: String
    ): CityDataSource = CityDataSourceImpl(context, gson, fileName)

    @Provides
    @CityListScope
    fun providesCityRepository(
        cityDataSource: CityDataSource,
        @Named(KEY_PAGE_SIZE) pageSize: Int,
        @Named(KEY_INITIAL_LOAD_SIZE) initialLoadSize: Int
    ): CityRepository = CityRepositoryImpl(cityDataSource, pageSize, initialLoadSize)
}