package com.aminography.navigation

import androidx.core.net.toUri
import com.aminography.navigation.argument.DeepLinkNavArg
import com.aminography.navigation.argument.KEY_NAV_ARG
import com.aminography.navigation.argument.encodeToBase64

/**
 * @author aminography
 */
abstract class BaseNavDestination<T : DeepLinkNavArg>(private val link: String) {

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