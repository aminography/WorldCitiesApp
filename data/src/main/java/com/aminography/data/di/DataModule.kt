package com.aminography.data.di

import com.aminography.data.CityRepositoryImpl
import com.aminography.data.datasource.CityDataSource
import com.aminography.data.datasource.CityDataSourceImpl
import com.aminography.domain.city.CityRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author aminography
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesCityDataSource(
        gson: Gson
    ): CityDataSource = CityDataSourceImpl(gson)

    @Provides
    fun providesCityRepository(
        cityDataSource: CityDataSource
    ): CityRepository = CityRepositoryImpl(cityDataSource)
}
