package com.aminography.domain

import com.aminography.domain.city.CityRepository
import com.aminography.domain.city.ClearCitiesCacheUseCase
import com.aminography.test.CoroutineTest
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClearCitiesCacheUseCaseTest : CoroutineTest() {

    private val cityRepository: CityRepository = mockk()

    @Test
    fun `clear cache of cities`() = runBlockingTest {
        // Given
        coEvery { cityRepository.clearCache() } just Runs

        val useCase = ClearCitiesCacheUseCase(testDispatcher, cityRepository)

        // When
        useCase(Unit)

        // Then
        coVerifySequence {
            cityRepository.clearCache()
        }
    }
}
