package com.aminography.worldcities.ui.citylist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.aminography.model.common.onLoading
import com.aminography.model.common.onSuccess
import com.aminography.worldcities.R
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.citylist.di.injectComponent
import com.aminography.worldcities.ui.citylist.vm.CityListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListFragment : BaseFragment(R.layout.fragment_city_list) {

    @Inject
    lateinit var viewModel: CityListViewModel

    @Inject
    lateinit var test: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        viewModel.test()

        lifecycleScope.launch {
            test.useCase(Unit).collect {
                it.onLoading { println("XXXXXXXXXXXXXXXXX  $it") }
                    .onSuccess { println("XXXXXXXXXXXXXXXXX  ${it?.size}") }
            }
        }
    }
}