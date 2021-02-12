package com.aminography.worldcities.ui.citylist.di

import com.aminography.core.di.AppComponent
import com.aminography.data.city.di.CityDataModule
import com.aminography.data.di.GsonComponent
import com.aminography.domain.di.DispatcherComponent
import com.aminography.scope.feature.CityListScope
import com.aminography.worldcities.ui.citylist.CityListFragment
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [CityListScope].
 *
 * @author aminography
 */
@CityListScope
@Component(
    dependencies = [
        AppComponent::class,
        DispatcherComponent::class,
        GsonComponent::class
    ],
    modules = [
        CityListModule::class,
        CityDataModule::class
    ]
)
interface CityListComponent {

    fun inject(fragment: CityListFragment)

    @Component.Builder
    interface Builder {

        fun appComponent(component: AppComponent): Builder

        fun dispatcherComponent(component: DispatcherComponent): Builder

        fun gsonComponent(component: GsonComponent): Builder

        @BindsInstance
        fun fragment(fragment: CityListFragment): Builder

        fun build(): CityListComponent
    }
}