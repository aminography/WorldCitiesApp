package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * An entity class that models a city.
 *
 * @param id unique id of the city.
 * @param name name of the city.
 * @param country country code of the city.
 * @param coord geographical coordination of the city.
 *
 * @author aminography
 */
data class City(
    @SerializedName("_id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("coord") val coord: Coordination
)