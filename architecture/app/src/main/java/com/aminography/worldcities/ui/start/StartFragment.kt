package com.aminography.worldcities.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aminography.navigation.navigateDeepLink
import com.aminography.worldcities.databinding.FragmentStartBinding
import com.aminography.worldcities.navigation.NavDestinations
import com.aminography.worldcities.ui.base.BaseFragment

/**
 * @author aminography
 */
class StartFragment : BaseFragment<FragmentStartBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStartBinding = FragmentStartBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        findNavController().run {
            popBackStack()
            navigateDeepLink(NavDestinations.CityList.deepLink(null))
        }
    }
}