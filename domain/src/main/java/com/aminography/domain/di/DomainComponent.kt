package com.aminography.domain.di

import dagger.Component

/**
 * @author aminography
 */
@Component(
    modules = [
        CoroutinesModule::class
    ]
)
@DomainScope
interface DomainComponent {

    @Component.Builder
    interface Builder {

        fun build(): DomainComponent
    }
}
