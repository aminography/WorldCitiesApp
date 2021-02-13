package com.aminography.worldcities.mapviewer

import com.aminography.model.city.Coordination
import com.aminography.test.CoroutineTest
import com.aminography.worldcities.InstantExecutorExtension
import com.aminography.worldcities.getOrAwaitValue
import com.aminography.worldcities.ui.model.MapViewerNavArg
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(InstantExecutorExtension::class)
class MapViewerViewModelTest : CoroutineTest() {

    private val mapViewerArg = MapViewerNavArg("city", "country", Coordination(0.0, 0.0))

    @Test
    fun `calling initMap should post city name on proper live-data`() {
        // Given
        val mapViewerViewModel = createViewModel()

        // When
        mapViewerViewModel.init(mapViewerArg)

        // Then
        assertEquals(mapViewerViewModel.cityName.getOrAwaitValue(), mapViewerArg.name)
    }

    @Test
    fun `calling initMap should post country code on proper live-data`() {
        // Given
        val mapViewerViewModel = createViewModel()

        // When
        mapViewerViewModel.init(mapViewerArg)

        // Then
        assertEquals(mapViewerViewModel.countryCode.getOrAwaitValue(), mapViewerArg.country)
    }

    @Test
    fun `calling initMap should post coordination on proper live-data`() {
        // Given
        val mapViewerViewModel = createViewModel()

        // When
        mapViewerViewModel.init(mapViewerArg)

        // Then
        assertEquals(mapViewerViewModel.coordination.getOrAwaitValue(), mapViewerArg.coord)
    }

    private fun createViewModel() = com.aminography.worldcities.mapviewer.vm.MapViewerViewModel()
}