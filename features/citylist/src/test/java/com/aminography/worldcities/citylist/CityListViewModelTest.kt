package com.aminography.worldcities.citylist

import com.aminography.domain.base.Result
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.test.CoroutineTest
import com.aminography.worldcities.citylist.vm.CityListViewModel
import com.aminography.worldcities.util.InstantExecutorExtension
import com.aminography.worldcities.util.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
    private val clearCitiesCacheUseCase: ClearCitiesCacheUseCase = mockk()

    @Test
    fun `load cities should cause showing loading`() {
        // Given
        every {
            loadCitiesUseCase(any())
        } returns flowOf(Result.Loading, Result.Success(Unit))

        // When
        val cityListViewModel = createViewModel()

        // Then
        assertEquals(cityListViewModel.loading.getOrAwaitValue(), true)

        verifySequence {
            loadCitiesUseCase(any())
        }
    }

    @Test
    fun `load cities successfully should show empty query`() {
        // Given
        every {
            loadCitiesUseCase(any())
        } returns flowOf(Result.Loading, Result.Success(Unit))

        // When
        val cityListViewModel = createViewModel()

        // Then
        assertEquals(cityListViewModel.queryLiveData.getOrAwaitValue(), "")

        verifySequence {
            loadCitiesUseCase(any())
        }
    }

    @Test
    fun `load cities with error should hide loading and show error message`() {
        // Given
        val expected = "test"
        every {
            loadCitiesUseCase(any())
        } returns flowOf(Result.Loading, Result.Error(Exception(expected)))

        // When
        val cityListViewModel = createViewModel()

        // Then
        assertEquals(cityListViewModel.loading.getOrAwaitValue(), false)
        assertEquals(cityListViewModel.errorMessage.getOrAwaitValue(), expected)

        verifySequence {
            loadCitiesUseCase(any())
        }
    }

    @Test
    fun `calling setQuery should post query value on queryLiveData`() {
        // Given
        val expected = "test"
        every {
            loadCitiesUseCase(any())
        } returns flowOf(Result.Loading, Result.Success(Unit))

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
        clearCitiesCacheUseCase
    )
}