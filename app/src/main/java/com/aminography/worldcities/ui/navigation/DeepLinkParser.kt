package com.aminography.worldcities.ui.navigation

import android.os.Parcelable
import androidx.core.net.toUri
import com.aminography.worldcities.ui.util.decodeFromBase64

/**
 * @author aminography
 */
class DeepLinkParser(private val deepLink: String) {

    fun <T : NavArgument> decodeArgument(creator: Parcelable.Creator<T>): T? =
        deepLink.toUri()
            .getQueryParameter("arg")
            ?.decodeFromBase64(creator)
}