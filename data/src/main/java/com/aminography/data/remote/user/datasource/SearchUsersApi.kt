package com.aminography.data.remote.user.datasource

import com.aminography.data.ACCEPT_HEADER
import com.aminography.data.remote.user.datasource.model.SearchUsersResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @author aminography
 */
interface SearchUsersApi {

    @Headers(ACCEPT_HEADER)
    @GET("search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<SearchUsersResponseModel>
}