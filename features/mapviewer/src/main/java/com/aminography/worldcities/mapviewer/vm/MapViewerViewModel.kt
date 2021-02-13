package com.aminography.worldcities.mapviewer.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aminography.model.city.Coordination
import com.aminography.worldcities.ui.model.MapViewerNavArg
import com.aminography.worldcities.ui.model.UserListNavArg
import com.aminography.worldcities.ui.navigation.NavDestination
import com.aminography.worldcities.ui.navigation.NavDirection
import com.aminography.worldcities.ui.util.SingleLiveEvent

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

    private val _navigation = SingleLiveEvent<NavDirection>()
    val navigation: LiveData<NavDirection> = _navigation

    fun init(navArg: MapViewerNavArg) {
        _cityName.postValue(navArg.name)
        _countryCode.postValue(navArg.country)
        _coordination.postValue(navArg.coord)
    }

    fun onShowUsersClicked() {
        _navigation.postValue(
            NavDestination.UserList.deepLinkWithArgument(
                UserListNavArg(_cityName.value ?: "")
            )
        )
    }
}