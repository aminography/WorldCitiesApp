package com.aminography.data.user.di

import com.aminography.data.user.UserRepositoryImpl
import com.aminography.data.user.datasource.SearchUsersApi
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.data.user.datasource.UserDataSourceImpl
import com.aminography.domain.user.UserRepository
import com.aminography.scope.annotation.FeatureScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * @author aminography
 */
@Module
class UserDataModule {

    @Provides
    internal fun providesSearchUsersApi(
        retrofit: Retrofit
    ): SearchUsersApi = retrofit.create(SearchUsersApi::class.java)

    @Provides
    internal fun providesUserDataSource(
        searchUsersApi: SearchUsersApi
    ): UserDataSource = UserDataSourceImpl(searchUsersApi)

    @FeatureScope
    @Provides
    internal fun providesUserRepository(
        userDataSource: UserDataSource
    ): UserRepository = UserRepositoryImpl(userDataSource)
}