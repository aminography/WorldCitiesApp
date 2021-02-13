package com.aminography.worldcities.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.aminography.model.city.Coordination
import com.aminography.worldcities.ui.navigation.NavArgument

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
data class MapViewerArg(
    val name: String,
    val country: String,
    val coord: Coordination
) : NavArgument {

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

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MapViewerArg> {
        override fun createFromParcel(parcel: Parcel): MapViewerArg {
            return MapViewerArg(parcel)
        }

        override fun newArray(size: Int): Array<MapViewerArg?> {
            return arrayOfNulls(size)
        }
    }
}