package com.aminography.data.user.di

import androidx.paging.PagingConfig
import com.aminography.data.KEY_INITIAL_LOAD_SIZE
import com.aminography.data.KEY_PAGE_SIZE
import com.aminography.data.user.UserRepositoryImpl
import com.aminography.data.user.datasource.SearchUsersApi
import com.aminography.data.user.datasource.UserDataSource
import com.aminography.data.user.datasource.UserDataSourceImpl
import com.aminography.data.user.paging.UserPagingFactory
import com.aminography.domain.user.UserRepository
import com.aminography.scope.annotation.FeatureScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author aminography
 */
@Module
class UserDataModule {

    @Provides
    @Named(KEY_PAGE_SIZE)
    internal fun providesPageSize(): Int = 40

    @Provides
    @Named(KEY_INITIAL_LOAD_SIZE)
    internal fun providesInitialLoadSize(): Int = 40

    @Provides
    internal fun providesPagingConfig(
        @Named(KEY_PAGE_SIZE) pageSize: Int,
        @Named(KEY_INITIAL_LOAD_SIZE) initialLoadSize: Int
    ): PagingConfig = PagingConfig(pageSize = pageSize, initialLoadSize = initialLoadSize)

    @Provides
    internal fun providesSearchUsersApi(
        retrofit: Retrofit
    ): SearchUsersApi = retrofit.create(SearchUsersApi::class.java)

    @Provides
    internal fun providesUserDataSource(
        searchUsersApi: SearchUsersApi
    ): UserDataSource = UserDataSourceImpl(searchUsersApi)

    @Provides
    internal fun providesUserPagingFactory(
        pagingConfig: PagingConfig
    ): UserPagingFactory = UserPagingFactory(pagingConfig)

    @FeatureScope
    @Provides
    internal fun providesUserRepository(
        userDataSource: UserDataSource,
        userPagingFactory: UserPagingFactory
    ): UserRepository = UserRepositoryImpl(userDataSource, userPagingFactory)
}