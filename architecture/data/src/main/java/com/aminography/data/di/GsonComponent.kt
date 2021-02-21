package com.aminography.data.di

import com.aminography.scope.ComponentHolder
import com.aminography.scope.annotation.FoundationScope
import com.google.gson.Gson
import dagger.Component

/**
 * @author aminography
 */
@FoundationScope
@Component(
    modules = [
        GsonModule::class
    ]
)
interface GsonComponent {

    fun exposesGson(): Gson

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