package com.aminography.worldcities.mapviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aminography.model.city.Coordination
import com.aminography.navigation.deepLinkNavArg
import com.aminography.navigation.observeNavigation
import com.aminography.worldcities.mapviewer.databinding.FragmentMapViewerBinding
import com.aminography.worldcities.mapviewer.di.injectComponent
import com.aminography.worldcities.mapviewer.vm.MapViewerViewModel
import com.aminography.worldcities.navigation.model.MapViewerNavArg
import com.aminography.worldcities.ui.base.BaseFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

/**
 * The fragment class to show the target location on an instance of [com.google.android.gms.maps.MapView].
 *
 * @author aminography
 */
class MapViewerFragment : BaseFragment<FragmentMapViewerBinding>(), OnMapReadyCallback {

    @Inject
    lateinit var viewModel: MapViewerViewModel

    private val navArg: MapViewerNavArg by deepLinkNavArg()

    private var googleMap: GoogleMap? = null
    private var coord: Coordination? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectComponent()
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMapViewerBinding = FragmentMapViewerBinding.inflate(inflater, container, false)

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) = with(binding) {
        mapView.onCreate(savedInstanceState?.getBundle(KEY_MAP_VIEW_BUNDLE))
        mapView.getMapAsync(this@MapViewerFragment)

        initViewModel()

        toolbar.setNavigationOnClickListener { viewModel.onNavigateUpClicked() }
        usersButton.setOnClickListener { viewModel.onShowUsersClicked() }
    }

    private fun initViewModel() {
        observeNavigation(viewModel.navigation)

        val owner = viewLifecycleOwner
        viewModel.init(navArg)
        viewModel.cityName.observe(owner) { binding.toolbar.title = it }
        viewModel.countryCode.observe(owner) { binding.toolbar.subtitle = it }
        viewModel.coordination.observe(owner) { coord = it; showLocation(it) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        coord?.let { showLocation(it) }
    }

    private fun showLocation(coord: Coordination) {
        val location = LatLng(coord.latitude, coord.longitude)
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                location,
                DEFAULT_CAMERA_ZOOM
            )
        )
        googleMap?.addMarker(MarkerOptions().position(location))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var bundle = outState.getBundle(KEY_MAP_VIEW_BUNDLE)
        if (bundle == null) {
            bundle = Bundle()
            outState.putBundle(KEY_MAP_VIEW_BUNDLE, bundle)
        }
        binding.mapView.onSaveInstanceState(bundle)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        binding.mapView.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        binding.mapView.onDestroy()
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    companion object {
        private const val KEY_MAP_VIEW_BUNDLE = "MAP_VIEW_BUNDLE"
        private const val DEFAULT_CAMERA_ZOOM = 11f
    }
}