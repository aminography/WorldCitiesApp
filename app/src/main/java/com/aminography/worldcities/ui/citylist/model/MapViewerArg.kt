package com.aminography.worldcities.ui.citylist.model

import android.os.Parcel
import android.os.Parcelable
import com.aminography.model.city.Coordination

/**
 * @author aminography
 */
data class MapViewerArg(
    val name: String,
    val country: String,
    val coord: Coordination
) : Parcelable {

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