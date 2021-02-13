package com.aminography.worldcities.navigation.core.argument

import android.os.Parcel
import android.os.Parcelable
import android.util.Base64


/**
 * @author aminography
 */

internal val DeepLinkNavArgument.encodeToBase64: String
    get() = Parcel.obtain().run {
        writeToParcel(this, 0)
        marshall().encodeBase64.also { recycle() }
    }

internal fun <T : DeepLinkNavArgument> String.decodeFromBase64(
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

private val ByteArray.encodeBase64: String
    get() = Base64.encodeToString(this, Base64.URL_SAFE).replace("\n", "#")

private val String.decodeBase64: ByteArray
    get() = Base64.decode(replace("#", "\n"), Base64.URL_SAFE)
