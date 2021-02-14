package com.aminography.worldcities.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.aminography.navigation.deepLinkNavArg
import com.aminography.navigation.observeNavigation
import com.aminography.worldcities.navigation.model.UserListNavArg
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.base.adapter.BaseDataHolder
import com.aminography.worldcities.ui.base.adapter.OnListItemClickListener
import com.aminography.worldcities.ui.util.toast
import com.aminography.worldcities.userlist.adapter.UserListAdapter
import com.aminography.worldcities.userlist.databinding.FragmentUserListBinding
import com.aminography.worldcities.userlist.di.injectComponent
import com.aminography.worldcities.userlist.vm.UserListViewModel
import javax.inject.Inject

/**
 * @author aminography
 */
class UserListFragment : BaseFragment<FragmentUserListBinding>(), OnListItemClickListener {

    @Inject
    lateinit var viewModel: UserListViewModel

    @Inject
    lateinit var adapter: UserListAdapter

    private val navArg: UserListNavArg by deepLinkNavArg()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserListBinding = FragmentUserListBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) = with(binding) {
        recyclerView.adapter = adapter
        adapter.setOnListItemClickListener(this@UserListFragment)
        adapter.addLoadStateListener { loadState ->
            loadState.decide(
                showEmptyState = { binding.emptyState.isVisible = it },
                showError = { context?.toast(it) }
            )
        }

        initViewModel()

        toolbar.setNavigationOnClickListener { viewModel.onNavigateUpClicked() }
    }

    private fun initViewModel() {
        observeNavigation(viewModel.navigation)

        val owner = viewLifecycleOwner
        viewModel.init(navArg)
        viewModel.cityName.observe(owner) { binding.toolbar.title = it }
        viewModel.searchResult.observe(owner) { adapter.submitData(lifecycle, it) }
        viewModel.errorMessage.observe(owner) { context?.toast(it) }
        viewModel.loading.observe(owner) { binding.progressBar.isVisible = it }
    }

    override fun onItemClicked(dataHolder: BaseDataHolder?) {
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