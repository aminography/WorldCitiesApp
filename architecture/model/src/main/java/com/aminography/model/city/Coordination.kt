package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * An entity class that models a geographical coordination.
 *
 * @param latitude latitude of the geographical coordination.
 * @param longitude longitude id of the geographical coordination.
 *
 * @author aminography
 */
data class Coordination(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)