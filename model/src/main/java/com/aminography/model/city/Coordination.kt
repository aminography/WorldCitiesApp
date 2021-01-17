package com.aminography.model.city

import com.google.gson.annotations.SerializedName

/**
 * @author aminography
 */
data class Coordination(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)