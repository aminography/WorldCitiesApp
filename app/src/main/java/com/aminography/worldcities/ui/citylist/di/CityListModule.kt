package com.aminography.worldcities.ui.citylist.di

import android.view.LayoutInflater
import com.aminography.scope.CityListScope
import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.citylist.adapter.CityListAdapter
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

    @Provides
    fun providesCityListViewModel(
        factory: CityListViewModelFactory,
        fragment: CityListFragment
    ): CityListViewModel = fragment.createViewModel(factory)

    @CityListScope
    @Provides
    fun providesLayoutInflater(
        fragment: CityListFragment
    ): LayoutInflater = LayoutInflater.from(fragment.requireContext())

    @Provides
    fun providesCityListAdapter(
        inflater: LayoutInflater
    ): CityListAdapter = CityListAdapter(inflater)
}