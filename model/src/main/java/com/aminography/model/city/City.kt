package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * An entity class that models a city
 *
 * @author aminography
 */
data class City(
    @SerializedName("_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("coord") val coord: Coordination
)