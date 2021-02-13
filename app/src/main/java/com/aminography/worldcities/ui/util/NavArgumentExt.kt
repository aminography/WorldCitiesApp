package com.aminography.worldcities.ui.util

import android.os.Parcel
import android.os.Parcelable
import com.aminography.worldcities.ui.navigation.NavArgument


/**
 * @author aminography
 */

val NavArgument.encodeToBase64: String
    get() = Parcel.obtain().run {
        writeToParcel(this, 0)
        marshall().encodeBase64.also { recycle() }
    }

fun <T : NavArgument> String.decodeFromBase64(
    creator: Parcelable.Creator<T>
): T = decodeBase64ToParcel.run {
    creator.createFromParcel(this).also { recycle() }
}

private val String.decodeBase64ToParcel: Parcel
    get() = decodeBase64.let {
        Parcel.obtain().apply {
            unmarshall(it, 0, it.size)
            setDataPosition(0)
        }
    }
