package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * An entity class that models a geographical coordination
 *
 * @param lat latitude of the geographical coordination
 * @param lon longitude id of the geographical coordination
 *
 * @author aminography
 */
data class Coordination(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)