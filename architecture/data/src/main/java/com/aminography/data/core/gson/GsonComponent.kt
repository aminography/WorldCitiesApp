package com.aminography.data.core.gson

import com.aminography.scope.ComponentHolder
import com.aminography.scope.annotation.AppScope
import com.google.gson.Gson
import dagger.Component
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author aminography
 */
@AppScope
@Component(
    modules = [
        GsonModule::class
    ]
)
interface GsonComponent {

    fun exposesGson(): Gson

    fun exposesGsonConverterFactory(): GsonConverterFactory

    @Component.Builder
    interface Builder {
        fun build(): GsonComponent
    }

    companion object : ComponentHolder<GsonComponent>() {
        override fun createComponent(): GsonComponent =
            DaggerGsonComponent.builder()
                .build()
    }
}