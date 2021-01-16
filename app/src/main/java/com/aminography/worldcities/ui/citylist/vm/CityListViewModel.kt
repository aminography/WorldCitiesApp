package com.aminography.worldcities.ui.citylist.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.aminography.worldcities.MainApplication

/**
 * @author aminography
 */
class CityListViewModel(
    application: Application
) : AndroidViewModel(application) {

    fun test() {
        println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
    }

    override fun onCleared() {
        getApplication<MainApplication>().cityListComponent = null
    }
}