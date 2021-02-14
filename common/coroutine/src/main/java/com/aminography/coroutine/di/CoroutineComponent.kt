package com.aminography.coroutine.di

import com.aminography.scope.ComponentHolder
import com.aminography.scope.annotation.AppScope
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author aminography
 */
@AppScope
@Component(
    modules = [
        DispatcherModule::class
    ]
)
interface CoroutineComponent {

    @DefaultDispatcher
    fun exposesDefaultDispatcher(): CoroutineDispatcher

    @IoDispatcher
    fun exposesIoDispatcher(): CoroutineDispatcher

    @MainDispatcher
    fun exposesMainDispatcher(): CoroutineDispatcher

    @Component.Builder
    interface Builder {
        fun build(): CoroutineComponent
    }

    companion object : ComponentHolder<CoroutineComponent>() {
        override fun createComponent(): CoroutineComponent =
            DaggerCoroutineComponent.builder()
                .build()
    }
}