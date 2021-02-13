package com.aminography.worldcities.navigation.model

import android.os.Parcel
import android.os.Parcelable
import com.aminography.worldcities.navigation.core.argument.DeepLinkNavArgument

/**
 * @author aminography
 */
data class UserListNavArg(
    val city: String
) : DeepLinkNavArgument {

    constructor(parcel: Parcel) : this(parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserListNavArg> {
        override fun createFromParcel(parcel: Parcel): UserListNavArg {
            return UserListNavArg(parcel)
        }

        override fun newArray(size: Int): Array<UserListNavArg?> {
            return arrayOfNulls(size)
        }
    }
}