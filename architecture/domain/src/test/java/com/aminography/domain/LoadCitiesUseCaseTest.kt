package com.aminography.domain

import com.aminography.domain.city.CityRepository
import com.aminography.domain.city.LoadCitiesUseCase
import com.aminography.test.CoroutineTest
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoadCitiesUseCaseTest : CoroutineTest() {

    private val cityRepository: CityRepository = mockk()

    @Test
    fun `load cities`() = runBlockingTest {
        // Given
        coEvery { cityRepository.loadCities() } returns Unit

        val useCase = LoadCitiesUseCase(testDispatcher, cityRepository)

        // When
        val result = useCase(Unit)

        // Then
        assert(result.isSuccess)
        assertEquals(result.getOrNull(), Unit)

        coVerifySequence {
            cityRepository.loadCities()
        }
    }
}
