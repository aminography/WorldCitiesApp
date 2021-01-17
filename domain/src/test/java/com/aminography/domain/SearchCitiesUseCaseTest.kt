package com.aminography.domain

import androidx.paging.PagingData
import com.aminography.domain.city.CityRepository
import com.aminography.domain.city.SearchCitiesUseCase
import com.aminography.model.city.City
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchCitiesUseCaseTest : CoroutineTest() {

    private val cityRepository: CityRepository = mockk()

    @Test
    fun `test searching cities via lowercase letters`() = runBlockingTest {
        // Given
        val queryLowerCase = "test"
        val queryMixedCase = "TeSt"
        val pagingData = PagingData.empty<City>()

        every { cityRepository.searchCities(queryLowerCase) } returns flowOf(pagingData)

        val useCase = SearchCitiesUseCase(cityRepository, testDispatcher)

        // When
        val result = useCase(queryMixedCase).toList()

        // Then
        assertEquals(result.size, 1)
        assertSame(result[0], pagingData)

        verifySequence {
            cityRepository.searchCities(queryLowerCase)
        }
    }
}