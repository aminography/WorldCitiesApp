package com.aminography.worldcities.citylist.di

import com.aminography.core.di.AppComponent
import com.aminography.coroutine.di.CoroutineComponent
import com.aminography.data.core.gson.GsonComponent
import com.aminography.scope.annotation.FeatureScope
import com.aminography.worldcities.citylist.CityListFragment
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [FeatureScope].
 *
 * @author aminography
 */
@FeatureScope
@Component(
    dependencies = [
        AppComponent::class,
        CoroutineComponent::class,
        GsonComponent::class
    ],
    modules = [
        CityListModule::class
    ]
)
interface CityListComponent {

    fun inject(fragment: CityListFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun coroutineComponent(component: CoroutineComponent): Builder
        fun gsonComponent(component: GsonComponent): Builder

        @BindsInstance
        fun fragment(fragment: CityListFragment): Builder
        fun build(): CityListComponent
    }
}