package com.aminography.worldcities.navigation.core

import androidx.core.net.toUri
import com.aminography.worldcities.navigation.core.argument.DeepLinkNavArg
import com.aminography.worldcities.navigation.core.argument.encodeToBase64

/**
 * @author aminography
 */
abstract class BaseNavDestination<T : DeepLinkNavArg>(private val link: String) {

    fun deepLink(): NavDirection.DeepLink =
        NavDirection.DeepLink(link.toUri())

    fun deepLinkWithArg(navArg: T): NavDirection.DeepLink =
        NavDirection.DeepLink(
            link.toUri()
                .buildUpon()
                .encodedQuery("arg=${navArg.encodeToBase64}")
                .build()
        )
}