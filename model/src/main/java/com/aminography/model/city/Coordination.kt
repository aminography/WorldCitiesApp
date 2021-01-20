package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * An entity class that models a geographical coordination
 *
 * @author aminography
 */
data class Coordination(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)