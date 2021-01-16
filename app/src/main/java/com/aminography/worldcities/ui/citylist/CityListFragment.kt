package com.aminography.worldcities.ui.citylist

import android.os.Bundle
import android.view.View
import com.aminography.worldcities.R
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.citylist.di.injectComponent
import com.aminography.worldcities.ui.citylist.vm.CityListViewModel
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListFragment : BaseFragment(R.layout.fragment_city_list) {

    @Inject
    lateinit var viewModel: CityListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        viewModel.queryCities.observe(viewLifecycleOwner) { println("XXX Success:  ${it?.size}") }
        viewModel.loadingMessage.observe(viewLifecycleOwner) { println("XXX Loading:  $it") }
        viewModel.errorMessage.observe(viewLifecycleOwner) { println("XXX Error:  $it") }

        viewModel.setQuery("")
    }
}