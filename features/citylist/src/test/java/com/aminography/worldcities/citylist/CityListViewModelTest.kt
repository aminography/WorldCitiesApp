package com.aminography.worldcities.citylist

import com.aminography.androidtest.InstantExecutorExtension
import com.aminography.androidtest.getOrAwaitValue
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.domain.city.SelectCityUseCase
import com.aminography.test.CoroutineTest
import com.aminography.worldcities.citylist.vm.CityListViewModel
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
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
class CityListViewModelTest : CoroutineTest() {

    private val defaultDispatcher: CoroutineDispatcher = testDispatcher
    private val loadCitiesUseCase: LoadCitiesUseCase = mockk()
    private val searchCitiesUseCase: SearchCitiesUseCase = mockk()
    private val selectCityUseCase: SelectCityUseCase = mockk()
    private val clearCitiesCacheUseCase: ClearCitiesCacheUseCase = mockk()

    @Test
    fun `load cities should cause showing loading`() = runBlockingTest {
        // Given
        coEvery { loadCitiesUseCase(any()) } returns Result.success(Unit)

        // When
        val cityListViewModel = createViewModel()

        // Then
        assertEquals(cityListViewModel.loading.getOrAwaitValue(), true)

        coVerifySequence {
            loadCitiesUseCase(any())
        }
    }

    @Test
    fun `load cities successfully should show empty query`() {
        // Given
        coEvery { loadCitiesUseCase(any()) } returns Result.success(Unit)

        // When
        val cityListViewModel = createViewModel()

        // Then
        assertEquals(cityListViewModel.queryLiveData.getOrAwaitValue(), "")

        coVerifySequence {
            loadCitiesUseCase(any())
        }
    }

    @Test
    fun `load cities with error should hide loading and show error message`() {
        // Given
        val expected = "test"
        coEvery { loadCitiesUseCase(any()) } returns Result.failure(Exception(expected))

        // When
        val cityListViewModel = createViewModel()

        // Then
        assertEquals(cityListViewModel.loading.getOrAwaitValue(), false)
        assertEquals(cityListViewModel.errorMessage.getOrAwaitValue(), expected)

        coVerifySequence {
            loadCitiesUseCase(any())
        }
    }

    @Test
    fun `calling setQuery should post query value on queryLiveData`() {
        // Given
        val expected = "test"
        coEvery { loadCitiesUseCase(any()) } returns Result.success(Unit)

        val cityListViewModel = createViewModel()

        // When
        cityListViewModel.setQuery(expected)

        // Then
        assertEquals(cityListViewModel.queryLiveData.getOrAwaitValue(), expected)
    }

    private fun createViewModel() = CityListViewModel(
        defaultDispatcher,
        loadCitiesUseCase,
        searchCitiesUseCase,
        selectCityUseCase,
        clearCitiesCacheUseCase
    )
}
