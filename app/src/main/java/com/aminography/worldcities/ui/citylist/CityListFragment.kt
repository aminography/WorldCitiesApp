package com.aminography.worldcities.ui.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.paging.LoadState
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

    @Inject
    lateinit var adapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCityListBinding = FragmentCityListBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        adapter.setOnListItemClickListener(this)
        adapter.addLoadStateListener { loadState ->
            binding.emptyState.isVisible =
                loadState.source.append is LoadState.NotLoading
                        && loadState.source.append.endOfPaginationReached
                        && adapter.itemCount == 0

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let { context?.toast(it.error.toString()) }
        }

        binding.recyclerView.adapter = adapter
        binding.searchEditText.addTextChangedListener { viewModel.setQuery(it.toString()) }

        initViewModel()
    }

    private fun initViewModel() {
        val owner = viewLifecycleOwner
        viewModel.searchResult.observe(owner) { adapter.submitData(lifecycle, it) }
        viewModel.errorMessage.observe(owner) { context?.toast(it) }
        viewModel.loading.observe(owner) { binding.progressBar.run { isVisible = it } }
    }

    override fun onItemClicked(dataHolder: BaseDataHolder?) {
    }
}