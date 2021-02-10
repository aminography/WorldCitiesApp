package com.aminography.worldcities.di

import android.content.Context
import com.aminography.domain.di.CoroutinesModule
import com.aminography.scope.AppScope
import com.aminography.worldcities.ui.citylist.di.CityListComponent
import com.aminography.worldcities.ui.mapviewer.di.MapViewerComponent
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [AppScope]. It is also responsible
 * for building relations to sub-components.
 *
 * @author aminography
 */
@AppScope
@Component(
    modules = [
        AppModule::class,
        CoroutinesModule::class
    ]
)
interface AppComponent {

    fun plusCityListComponent(): CityListComponent.Builder

    fun plusCityMapComponent(): MapViewerComponent.Builder

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}