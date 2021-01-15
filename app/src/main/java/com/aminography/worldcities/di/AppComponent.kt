package com.aminography.worldcities.di

import com.aminography.domain.di.CoroutinesModule
import com.aminography.domain.di.DomainComponent
import com.aminography.worldcities.MainApplication
import com.aminography.worldcities.di.context.ContextComponent
import com.aminography.worldcities.ui.citylist.CityListFragment
import dagger.BindsInstance
import dagger.Component

/**
 * @author aminography
 */
@Component(
    modules = [CoroutinesModule::class],
//    dependencies = [DomainComponent::class]
)
@AppScope
interface AppComponent {

    fun inject(app: MainApplication)

    fun inject(fragment: CityListFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun contextComponent(
            component: ContextComponent
        ): Builder

        @BindsInstance
        fun domainComponent(
            component: DomainComponent
        ): Builder
    }
}