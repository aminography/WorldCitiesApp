package com.aminography.data.di

import com.aminography.core.di.AppComponent
import com.aminography.data.local.pref.di.PrefModule
import com.aminography.scope.FoundationScope
import dagger.Component
import retrofit2.Retrofit

/**
 * @author aminography
 */
@FoundationScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        NetworkModule::class,
        PrefModule::class
    ]
)
interface NetworkComponent {

    fun exposesRetrofit(): Retrofit

    @Component.Builder
    interface Builder {

        fun appComponent(component: AppComponent): Builder

        fun build(): NetworkComponent
    }
}