package com.aminography.navigation

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.aminography.navigation.argument.DeepLinkNavArg
import com.aminography.navigation.argument.DeepLinkNavArgLazy

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

@MainThread
inline fun <reified T : DeepLinkNavArg> Fragment.deepLinkNavArg() =
    DeepLinkNavArgLazy(T::class) {
        arguments ?: throw IllegalStateException("Fragment $this has null arguments")
    }