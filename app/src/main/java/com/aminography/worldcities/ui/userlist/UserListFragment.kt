package com.aminography.worldcities.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserListBinding = FragmentUserListBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) = with(binding) {
        initViewModel()
    }

    private fun initViewModel() {
    }
}