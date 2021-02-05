package com.aminography.worldcities.di

import com.aminography.worldcities.ui.citylist.di.CityListComponent
import com.aminography.worldcities.ui.mapviewer.di.MapViewerComponent

/**
 * @author aminography
 */
@Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
class ComponentManager(val appComponent: AppComponent) {

    private val componentMap = mutableMapOf<Class<BaseComponent>, BaseComponent>()

    fun retainComponent(component: BaseComponent) {
        componentMap[component.javaClass] = component
    }

    fun clearCityListComponent() {
        componentMap.remove(CityListComponent::class.java)
    }

    fun clearMapViewerComponent() {
        componentMap.remove(MapViewerComponent::class.java)
    }
}