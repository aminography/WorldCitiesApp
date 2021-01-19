package com.aminography.worldcities.ui.mapviewer.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aminography.model.city.Coordination
import com.aminography.worldcities.ui.citylist.model.MapViewerArg
import javax.inject.Inject

/**
 * @author aminography
 */
class MapViewerViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _cityName

    private val _countryName = MutableLiveData<String>()
    val countryName: LiveData<String> = _countryName

    private val _coordination = MutableLiveData<Coordination>()
    val coordination: LiveData<Coordination> = _coordination

    fun initMap(arg: MapViewerArg) {
        _cityName.postValue(arg.name)
        _countryName.postValue(arg.country)
        _coordination.postValue(arg.coord)
    }
}