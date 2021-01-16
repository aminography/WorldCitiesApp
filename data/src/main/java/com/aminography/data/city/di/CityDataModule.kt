package com.aminography.data.city.di

import android.content.Context
import com.aminography.data.city.CityRepositoryImpl
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.datasource.CityDataSourceImpl
import com.aminography.data.util.KEY_FILE_NAME
import com.aminography.domain.city.CityRepository
import com.aminography.scope.CityListScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author aminography
 */
@Module
class CityDataModule {

    @Provides
    @Named(KEY_FILE_NAME)
    fun providesFileName(): String = "cities.json"

    @Provides
    @CityListScope
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesCityDataSource(
        context: Context,
        gson: Gson,
        @Named(KEY_FILE_NAME) fileName: String
    ): CityDataSource = CityDataSourceImpl(context, gson, fileName)

    @Provides
    @CityListScope
    fun providesCityRepository(
        cityDataSource: CityDataSource
    ): CityRepository = CityRepositoryImpl(cityDataSource)
}