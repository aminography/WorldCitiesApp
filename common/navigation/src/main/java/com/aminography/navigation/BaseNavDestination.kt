package com.aminography.navigation

import android.os.Parcelable
import androidx.core.net.toUri
import com.aminography.navigation.argument.encodeToBase64

/**
 * @author aminography
 */
abstract class BaseNavDestination<T : Parcelable>(private val link: String) {

    fun deepLink(): NavDirection.DeepLink =
        NavDirection.DeepLink(
            link.toUri()
        )

    fun deepLinkWithArg(navArg: T): NavDirection.DeepLink =
        NavDirection.DeepLink(
            link.toUri()
                .buildUpon()
                .encodedQuery("${KEY_NAV_ARG}=${navArg.encodeToBase64}")
                .build()
        )
}