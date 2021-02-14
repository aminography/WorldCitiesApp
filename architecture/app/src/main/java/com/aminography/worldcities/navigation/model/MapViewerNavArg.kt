package com.aminography.worldcities.navigation.model

import android.os.Parcel
import android.os.Parcelable
import com.aminography.model.city.Coordination
import com.aminography.navigation.argument.DeepLinkNavArg

/**
 * The argument for the [MapViewerFragment], sent via
 * safe-args.
 *
 * @param name the name of the target city.
 * @param country the country code of the target city.
 * @param coord the geographical coordination of the target city.
 *
 * @author aminography
 */
data class MapViewerNavArg(
    val name: String,
    val country: String,
    val coord: Coordination
) : DeepLinkNavArg {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        Coordination(
            parcel.readDouble(),
            parcel.readDouble()
        )
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(country)
        parcel.writeDouble(coord.lat)
        parcel.writeDouble(coord.lon)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<MapViewerNavArg> {
        override fun createFromParcel(parcel: Parcel): MapViewerNavArg {
            return MapViewerNavArg(parcel)
        }

        override fun newArray(size: Int): Array<MapViewerNavArg?> {
            return arrayOfNulls(size)
        }
    }
}