package com.aminography.data.core.network

import com.aminography.core.di.AppComponent
import com.aminography.data.core.gson.GsonComponent
import com.aminography.data.core.persistent.di.PersistentDataComponent
import com.aminography.scope.ComponentHolder
import com.aminography.scope.annotation.foundation.NetworkScope
import dagger.Component
import retrofit2.Retrofit

/**
 * @author aminography
 */
@NetworkScope
@Component(
    dependencies = [
        AppComponent::class,
        GsonComponent::class,
        PersistentDataComponent::class
    ],
    modules = [
        NetworkModule::class
    ]
)
interface NetworkComponent {

    fun exposesRetrofit(): Retrofit

    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder
        fun gsonComponent(component: GsonComponent): Builder
        fun persistentDataComponent(component: PersistentDataComponent): Builder

        fun build(): NetworkComponent
    }

    companion object : ComponentHolder<NetworkComponent>() {
        override fun createComponent(): NetworkComponent =
            DaggerNetworkComponent.builder()
                .appComponent(AppComponent.get())
                .gsonComponent(GsonComponent.get())
                .persistentDataComponent(PersistentDataComponent.get())
                .build()
    }
}