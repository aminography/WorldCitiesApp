package com.aminography.data.core.persistent.di

import com.aminography.core.di.AppComponent
import com.aminography.data.core.persistent.pref.di.PrefModule
import com.aminography.data.core.persistent.pref.settings.SettingsDataSource
import com.aminography.scope.ComponentHolder
import com.aminography.scope.annotation.PersistentDataScope
import dagger.Component

/**
 * @author aminography
 */
@PersistentDataScope
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        PrefModule::class
    ]
)
interface PersistentDataComponent {

    fun exposesSettingsDataSource(): SettingsDataSource

    @Component.Builder
    interface Builder {
        fun appComponent(component: AppComponent): Builder

        fun build(): PersistentDataComponent
    }

    companion object : ComponentHolder<PersistentDataComponent>() {
        override fun createComponent(): PersistentDataComponent =
            DaggerPersistentDataComponent.builder()
                .appComponent(AppComponent.get())
                .build()
    }
}