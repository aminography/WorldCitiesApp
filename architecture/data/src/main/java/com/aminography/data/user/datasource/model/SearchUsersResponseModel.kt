package com.aminography.data.user.datasource.model

import com.aminography.model.user.GithubUser
import com.google.gson.annotations.SerializedName

/**
 * @author aminography
 */
data class SearchUsersResponseModel(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<GithubUser>
)