package com.aminography.worldcities.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * The argument for the [MapViewerFragment], sent via
 * safe-args.
 *
 * @param name the name of the target city.
 * @param country the country code of the target city.
 * @param latitude latitude of the geographical coordination of the target city.
 * @param longitude longitude id of the geographical coordination of the target city.
 * @author aminography
 */
@Parcelize
data class MapViewerNavArg(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable