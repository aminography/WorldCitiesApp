package com.aminography.worldcities.navigation.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aminography.worldcities.R

/**
 * @author aminography
 */

fun NavController.navigateDeepLink(deepLink: NavDirection.DeepLink) {
    navigate(deepLink.uri, navOptions)
}

private val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun <T : NavDirection> Fragment.observeNavigation(liveData: LiveData<T>) {
    liveData.observe(viewLifecycleOwner) {
        when (it) {
            is NavDirection.Up -> findNavController().navigateUp()
            is NavDirection.DeepLink -> findNavController().navigateDeepLink(it)
        }
    }
}