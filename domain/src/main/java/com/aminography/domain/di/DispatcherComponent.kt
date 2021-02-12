package com.aminography.domain.di

import com.aminography.scope.ComponentHolder
import com.aminography.scope.app.AppScope
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
interface DispatcherComponent {

    @DefaultDispatcher
    fun exposesDefaultDispatcher(): CoroutineDispatcher

    @IoDispatcher
    fun exposesIoDispatcher(): CoroutineDispatcher

    @MainDispatcher
    fun exposesMainDispatcher(): CoroutineDispatcher

    @Component.Builder
    interface Builder {
        fun build(): DispatcherComponent
    }

    companion object : ComponentHolder<DispatcherComponent>() {
        override fun createComponent(): DispatcherComponent =
            DaggerDispatcherComponent.builder()
                .build()
    }
}