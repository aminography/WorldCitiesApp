package com.aminography.data.core.network

import com.aminography.core.di.AppComponent
import com.aminography.data.core.gson.GsonComponent
import com.aminography.data.local.pref.di.PrefModule
import com.aminography.scope.ComponentHolder
import com.aminography.scope.annotation.FoundationScope
import dagger.Component
import retrofit2.Retrofit

/**
 * @author aminography
 */
@FoundationScope
@Component(
    dependencies = [
        AppComponent::class,
        GsonComponent::class
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
        fun gsonComponent(component: GsonComponent): Builder
        fun build(): NetworkComponent
    }

    companion object : ComponentHolder<NetworkComponent>() {
        override fun createComponent(): NetworkComponent =
            DaggerNetworkComponent.builder()
                .appComponent(AppComponent.get())
                .gsonComponent(GsonComponent.get())
                .build()
    }
}