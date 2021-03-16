package com.aminography.navigation

import android.net.Uri
import androidx.navigation.NavOptions

/**
 * @author aminography
 */
sealed class NavDirection {

    object Up : NavDirection()

    class DeepLink(val uri: Uri, val navOptions: NavOptions?) : NavDirection()
}