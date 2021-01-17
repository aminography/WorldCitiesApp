package com.aminography.domain

import com.aminography.domain.base.CoroutineTest
import com.aminography.domain.city.CityRepository
import com.aminography.domain.city.ClearCitiesCacheUseCase
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
    fun `clear cache of cities successfully`() = runBlockingTest {
        // Given
        coEvery { cityRepository.clearCache() } just Runs

        val useCase = ClearCitiesCacheUseCase(cityRepository)

        // When
        useCase(Unit)

        // Then
        coVerifySequence {
            cityRepository.clearCache()
        }
    }
}