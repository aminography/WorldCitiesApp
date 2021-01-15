package com.aminography.worldcities.di

import com.aminography.data.di.DataModule
import com.aminography.domain.di.CoroutinesModule
import com.aminography.worldcities.MainApplication
import com.aminography.worldcities.di.context.ContextComponent
import com.aminography.worldcities.ui.citylist.CityListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author aminography
 */
@Component(
    modules = [
        CoroutinesModule::class,
        DataModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(app: MainApplication)

    fun inject(fragment: CityListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance contextComponent: ContextComponent
        ): AppComponent
    }
}