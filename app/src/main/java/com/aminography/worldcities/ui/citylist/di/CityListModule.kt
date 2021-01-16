package com.aminography.worldcities.ui.citylist.di

import com.aminography.domain.city.GetCityTreeFlowUseCase
import com.aminography.scope.CityListScope
import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.citylist.Test
import com.aminography.worldcities.ui.citylist.vm.CityListViewModel
import com.aminography.worldcities.ui.citylist.vm.CityListViewModelFactory
import com.aminography.worldcities.ui.util.createViewModel
import dagger.Module
import dagger.Provides

/**
 * @author aminography
 */
@Module
class CityListModule {

    @CityListScope
    @Provides
    fun providesTest(
        useCase: GetCityTreeFlowUseCase
    ): Test = Test(useCase)

    @Provides
    fun providesCityListViewModel(
        factory: CityListViewModelFactory,
        fragment: CityListFragment
    ): CityListViewModel = fragment.createViewModel(factory)
}