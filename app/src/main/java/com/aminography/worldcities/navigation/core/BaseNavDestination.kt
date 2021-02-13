package com.aminography.worldcities.navigation.core

import androidx.core.net.toUri
import com.aminography.worldcities.navigation.core.argument.DeepLinkNavArgument
import com.aminography.worldcities.navigation.core.argument.encodeToBase64

/**
 * @author aminography
 */
abstract class BaseNavDestination<T : DeepLinkNavArgument>(private val link: String) {

    fun deepLink(): NavDirection.DeepLink =
        NavDirection.DeepLink(link.toUri())

    fun deepLinkWithArgument(navArgument: T): NavDirection.DeepLink =
        NavDirection.DeepLink(
            link.toUri()
                .buildUpon()
                .encodedQuery("arg=${navArgument.encodeToBase64}")
                .build()
        )
}