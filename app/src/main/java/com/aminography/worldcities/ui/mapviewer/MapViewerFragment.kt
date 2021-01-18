package com.aminography.worldcities.ui.mapviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aminography.worldcities.databinding.FragmentMapViewerBinding
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.mapviewer.di.injectComponent
import com.aminography.worldcities.ui.mapviewer.vm.MapViewerViewModel
import javax.inject.Inject

/**
 * @author aminography
 */
class MapViewerFragment : BaseFragment<FragmentMapViewerBinding>() {

    @Inject
    lateinit var viewerViewModel: MapViewerViewModel

    private val args: MapViewerFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMapViewerBinding = FragmentMapViewerBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        initViewModel()
    }

    private fun initViewModel() {
        val owner = viewLifecycleOwner
        viewerViewModel.toString()
//        viewModel.errorMessage.observe(owner) { context?.toast(it) }
    }
}