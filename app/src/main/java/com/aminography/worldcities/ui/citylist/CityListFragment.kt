package com.aminography.worldcities.ui.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aminography.worldcities.databinding.FragmentCityListBinding
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder
import com.aminography.worldcities.ui.base.adapter.OnListItemClickListener
import com.aminography.worldcities.ui.citylist.adapter.CityListAdapter
import com.aminography.worldcities.ui.citylist.di.injectComponent
import com.aminography.worldcities.ui.citylist.vm.CityListViewModel
import javax.inject.Inject

/**
 * @author aminography
 */
class CityListFragment : BaseFragment<FragmentCityListBinding>(), OnListItemClickListener {

    @Inject
    lateinit var viewModel: CityListViewModel

    private val adapter: CityListAdapter by lazy {
        CityListAdapter().also {
            it.setOnListItemClickListener(this)
            binding.recyclerView.adapter = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCityListBinding = FragmentCityListBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        viewModel.queryCities.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadingMessage.observe(viewLifecycleOwner) { println("XXX Loading: $it") }
        viewModel.errorMessage.observe(viewLifecycleOwner) { println("XXX Error: $it") }

        viewModel.setQuery("*")

        viewModel.setQuery("Rio")
    }

    override fun onItemClicked(dataHolder: BaseDataHolder) {
    }
}