package com.aminography.navigation

import android.net.Uri

/**
 * @author aminography
 */
sealed class NavDirection {

    object Up : NavDirection()

    class DeepLink(val uri: Uri) : NavDirection()
}