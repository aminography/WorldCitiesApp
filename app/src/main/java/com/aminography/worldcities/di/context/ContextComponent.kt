package com.aminography.worldcities.di.context

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author aminography
 */
@Component(
    modules = [
        ContextModule::class
    ]
)
@Singleton
interface ContextComponent {

    fun provideApplication(): Application

    @Component.Builder
    interface Builder {

        fun build(): ContextComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
