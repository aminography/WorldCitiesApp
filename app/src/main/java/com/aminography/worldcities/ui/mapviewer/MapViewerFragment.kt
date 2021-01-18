package com.aminography.worldcities.ui.mapviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.aminography.model.city.Coordination
import com.aminography.worldcities.databinding.FragmentMapViewerBinding
import com.aminography.worldcities.ui.base.BaseFragment
import com.aminography.worldcities.ui.mapviewer.di.injectComponent
import com.aminography.worldcities.ui.mapviewer.vm.MapViewerViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

/**
 * @author aminography
 */
class MapViewerFragment : BaseFragment<FragmentMapViewerBinding>(), OnMapReadyCallback {

    @Inject
    lateinit var viewModel: MapViewerViewModel

    private val args: MapViewerFragmentArgs by navArgs()

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

    override fun onInitViews(rootView: View, savedInstanceState: Bundle?) {
        binding.mapView.onCreate(savedInstanceState?.getBundle(KEY_MAP_VIEW_BUNDLE))
        binding.mapView.getMapAsync(this)
        initViewModel()
    }

    private fun initViewModel() {
        val owner = viewLifecycleOwner
        viewModel.initMap(args.mapViewerArg)
        viewModel.cityName.observe(owner) { }
        viewModel.countryName.observe(owner) { }
        viewModel.coordination.observe(owner) { coord = it; animateCamera(it) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        coord?.let { animateCamera(it) }
    }

    private fun animateCamera(coord: Coordination) {
        googleMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(coord.lat, coord.lon),
                DEFAULT_CAMERA_ZOOM
            )
        )
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