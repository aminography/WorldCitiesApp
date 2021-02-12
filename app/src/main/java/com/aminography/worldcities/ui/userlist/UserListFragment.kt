package com.aminography.worldcities.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aminography.worldcities.databinding.FragmentUserListBinding
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.userlist.di.injectComponent
import com.aminography.worldcities.ui.userlist.vm.UserListViewModel
import javax.inject.Inject

/**
 * @author aminography
 */
class UserListFragment : BaseFragment<FragmentUserListBinding>() {

    @Inject
    lateinit var viewModel: UserListViewModel

    private val args: UserListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserListBinding = FragmentUserListBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) = with(binding) {
        toolbar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        initViewModel()
    }

    private fun initViewModel() {
        val owner = viewLifecycleOwner
        viewModel.init(args.cityName)
        viewModel.cityName.observe(owner) { binding.toolbar.title = it }
    }
}