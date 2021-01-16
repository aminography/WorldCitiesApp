package com.aminography.worldcities.ui.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.aminography.worldcities.databinding.FragmentCityListBinding
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder
import com.aminography.worldcities.ui.base.adapter.OnListItemClickListener
import com.aminography.worldcities.ui.citylist.adapter.CityListAdapter
import com.aminography.worldcities.ui.citylist.di.injectComponent
import com.aminography.worldcities.ui.citylist.vm.CityListViewModel
import com.aminography.worldcities.ui.util.toast
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
        viewModel.loading.observe(viewLifecycleOwner) { binding.progressBar.run { if (it) show() else hide() } }
        viewModel.errorMessage.observe(viewLifecycleOwner) { context?.toast(it) }

        binding.searchEditText.addTextChangedListener {
            viewModel.setQuery(it.toString())
        }
    }

    override fun onItemClicked(dataHolder: BaseDataHolder) {
    }
}