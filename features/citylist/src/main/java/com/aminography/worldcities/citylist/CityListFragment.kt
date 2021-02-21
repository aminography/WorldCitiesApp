package com.aminography.worldcities.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.aminography.navigation.observeNavigation
import com.aminography.worldcities.citylist.adapter.CityItemDataHolder
import com.aminography.worldcities.citylist.adapter.CityListAdapter
import com.aminography.worldcities.citylist.databinding.FragmentCityListBinding
import com.aminography.worldcities.citylist.di.injectComponent
import com.aminography.worldcities.citylist.vm.CityListViewModel
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder
import com.aminography.worldcities.ui.base.adapter.OnListItemClickListener
import com.aminography.worldcities.ui.util.*
import javax.inject.Inject

/**
 * The fragment class to show list of cities with the ability to perform a prefix search on it.
 *
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

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) = with(binding) {
        recyclerView.adapter = adapter
        adapter.setOnListItemClickListener(this@CityListFragment)
        adapter.addLoadStateListener { loadState ->
            loadState.decide(
                showEmptyState = { binding.emptyState.isVisible = it },
                showError = { context?.toast(it) }
            )
        }

        clearImageView.setOnClickListener { searchEditText.setText("") }
        searchEditText.addTextChangedListener {
            viewModel.setQuery(it.toString())
            clearImageView.isVisible = it?.toString().isNullOrEmpty().not()
        }

        initViewModel()
    }

    private fun initViewModel() {
        observeNavigation(viewModel.navigation)

        val owner = viewLifecycleOwner
        viewModel.searchResult.observe(owner) { adapter.submitData(lifecycle, it) }
        viewModel.errorMessage.observe(owner) { context?.toast(it) }
        viewModel.loading.observe(owner) { binding.progressBar.isVisible = it }
    }

    override fun onPause() {
        binding.searchEditText.hideKeyboard()
        super.onPause()
    }

    override fun onItemClicked(dataHolder: BaseDataHolder?) {
        (dataHolder as? CityItemDataHolder)?.run { viewModel.onCityClicked(city) }
    }

    private inline fun CombinedLoadStates.decide(
        showEmptyState: (Boolean) -> Unit,
        showError: (String) -> Unit
    ) {
        showEmptyState(
            source.append is LoadState.NotLoading
                    && source.append.endOfPaginationReached
                    && adapter.itemCount == 0
        )
        val errorState = source.append as? LoadState.Error
            ?: source.prepend as? LoadState.Error
            ?: append as? LoadState.Error
            ?: prepend as? LoadState.Error
        errorState?.let { showError(it.error.toString()) }
    }
}