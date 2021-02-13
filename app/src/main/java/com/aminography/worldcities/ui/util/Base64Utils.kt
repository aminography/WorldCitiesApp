package com.aminography.worldcities.ui.util

import android.util.Base64

/**
 * @author aminography
 */

val ByteArray.encodeBase64: String
    get() = Base64.encodeToString(this, Base64.URL_SAFE)

val String.decodeBase64: ByteArray
    get() = Base64.decode(this, Base64.URL_SAFE)