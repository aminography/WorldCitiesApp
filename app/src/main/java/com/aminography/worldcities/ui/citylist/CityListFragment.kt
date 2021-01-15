package com.aminography.worldcities.ui.citylist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.aminography.domain.city.GetCitiesFlowUseCase
import com.aminography.worldcities.R
import com.aminography.worldcities.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListFragment : BaseFragment(R.layout.fragment_city_list) {

    @Inject
    lateinit var useCase: GetCitiesFlowUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            useCase(Unit).collect {
                println("XXXXXXXXXXXXXXXXX  $it")
            }
        }
    }
}