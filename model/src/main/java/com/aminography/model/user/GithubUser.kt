package com.aminography.model.user

import com.google.gson.annotations.SerializedName

/**
 * @author aminography
 */
data class GithubUser(
    @SerializedName("id") val id: Int,
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("repos_url") val reposUrl: String,
    @SerializedName("starred_url") val starredUrl: String
)