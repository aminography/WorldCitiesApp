package com.aminography.worldcities.ui.util

import android.os.Parcel

import android.os.Parcelable


/**
 * @author aminography
 */

val Parcelable.encodeToBase64: String
    get() = Parcel.obtain().run {
        writeToParcel(this, 0)
        marshall().encodeBase64.also { recycle() }
    }

val String.decodeBase64ToParcel: Parcel
    get() = decodeBase64.let {
        Parcel.obtain().apply {
            unmarshall(it, 0, it.size)
            setDataPosition(0)
        }
    }

fun <T> String.decodeBase64ToParcel(
    creator: Parcelable.Creator<T>
): T = decodeBase64ToParcel.run {
    creator.createFromParcel(this).also { recycle() }
}