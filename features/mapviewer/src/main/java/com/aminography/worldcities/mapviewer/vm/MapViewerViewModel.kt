package com.aminography.worldcities.mapviewer.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aminography.model.city.Coordination
import com.aminography.worldcities.ui.citylist.model.MapViewerArg

/**
 * The [ViewModel] class for the map-viewer user interface.
 *
 * @author aminography
 */
class MapViewerViewModel : ViewModel() {

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _countryCode = MutableLiveData<String>()
    val countryCode: LiveData<String> = _countryCode

    private val _coordination = MutableLiveData<Coordination>()
    val coordination: LiveData<Coordination> = _coordination

    fun init(arg: MapViewerArg) {
        _cityName.postValue(arg.name)
        _countryCode.postValue(arg.country)
        _coordination.postValue(arg.coord)
    }
}