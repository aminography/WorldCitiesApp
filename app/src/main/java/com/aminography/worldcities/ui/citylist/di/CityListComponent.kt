package com.aminography.worldcities.ui.citylist.di

import com.aminography.data.city.di.CityDataModule
import com.aminography.scope.CityListScope
import com.aminography.worldcities.di.BaseComponent
import com.aminography.worldcities.ui.citylist.CityListFragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * A dagger component class providing dependencies related to the [CityListScope].
 *
 * @author aminography
 */
@CityListScope
@Subcomponent(
    modules = [
        CityListModule::class,
        CityDataModule::class
    ]
)
interface CityListComponent : BaseComponent {

    fun inject(fragment: CityListFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): CityListComponent

        @BindsInstance
        fun cityListFragment(fragment: CityListFragment): Builder
    }
}