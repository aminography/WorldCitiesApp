package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * @author aminography
 */
data class City(
    @SerializedName("_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("coord") val coord: Coordination
)