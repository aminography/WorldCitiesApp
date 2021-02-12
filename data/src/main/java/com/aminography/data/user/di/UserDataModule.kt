package com.aminography.data.user.di

import com.aminography.data.user.UserRepositoryImpl
import com.aminography.data.user.datasource.SearchUsersApi
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.data.user.datasource.UserDataSourceImpl
import com.aminography.domain.user.UserRepository
import com.aminography.scope.feature.UserListScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * @author aminography
 */
@Module
class UserDataModule {

    @UserListScope
    @Provides
    internal fun providesSearchUsersApi(
        retrofit: Retrofit
    ): SearchUsersApi = retrofit.create(SearchUsersApi::class.java)

    @UserListScope
    @Provides
    internal fun providesUserDataSource(
        searchUsersApi: SearchUsersApi
    ): UserDataSource = UserDataSourceImpl(searchUsersApi)

    @UserListScope
    @Provides
    internal fun providesUserRepository(
        userDataSource: UserDataSource
    ): UserRepository = UserRepositoryImpl(userDataSource)
}