package com.aminography.data.di

import com.aminography.scope.FoundationScope
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
}