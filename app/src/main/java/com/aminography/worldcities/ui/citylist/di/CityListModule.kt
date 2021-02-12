package com.aminography.worldcities.ui.citylist.di

import android.view.LayoutInflater
import com.aminography.scope.annotation.FeatureScope
import com.aminography.worldcities.ui.citylist.CityListFragment
import com.aminography.worldcities.ui.citylist.adapter.CityListAdapter
import com.aminography.worldcities.ui.citylist.vm.CityListViewModel
import com.aminography.worldcities.ui.citylist.vm.CityListViewModelFactory
import com.aminography.worldcities.ui.util.createViewModel
import dagger.Module
import dagger.Provides

/**
 * A dagger module class defining how to provide city-list related dependencies for injection.
 *
 * @author aminography
 */
@Module
class CityListModule {

    @FeatureScope
    @Provides
    fun providesCityListViewModel(
        factory: CityListViewModelFactory,
        fragment: CityListFragment
    ): CityListViewModel = fragment.createViewModel(factory)

    @FeatureScope
    @Provides
    fun providesLayoutInflater(
        fragment: CityListFragment
    ): LayoutInflater = LayoutInflater.from(fragment.requireContext())

    @FeatureScope
    @Provides
    fun providesCityListAdapter(
        inflater: LayoutInflater
    ): CityListAdapter = CityListAdapter(inflater)
}