package com.aminography.core.di

import android.app.Application
import android.content.Context
import com.aminography.domain.di.DispatcherModule
import com.aminography.scope.ComponentHolder
import com.aminography.scope.app.AppScope
import dagger.BindsInstance
import dagger.Component
import java.lang.ref.WeakReference

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

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    companion object : ComponentHolder<AppComponent>() {

        private lateinit var context: WeakReference<Context>

        internal fun from(context: Context) {
            this.context = WeakReference(context)
            createComponent()
        }

        override fun createComponent(): AppComponent =
            context.get()?.let {
                DaggerAppComponent.builder()
                    .context(it)
                    .build()
            } ?: throw RuntimeException("AppComponent is not initialized in the application!")
    }
}