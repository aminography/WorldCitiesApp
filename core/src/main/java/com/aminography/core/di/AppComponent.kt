package com.aminography.core.di

import android.app.Application
import android.content.Context
import com.aminography.domain.di.DispatcherModule
import com.aminography.scope.AppScope
import dagger.BindsInstance
import dagger.Component

/**
 * A dagger component class providing dependencies related to the [AppScope]. It is also responsible
 * for building relations to sub-components.
 *
 * @author aminography
 */
@AppScope
@Component(
    modules = [
        AppModule::class,
        DispatcherModule::class
    ]
)
interface AppComponent {

    fun exposesContext(): Context

    fun exposesApplication(): Application

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}