package com.aminography.data

import androidx.paging.PagingData
import com.aminography.data.city.CityRepositoryImpl
import com.aminography.data.city.datasource.CityDataSource
import com.aminography.data.city.paging.CityPagingFactory
import com.aminography.domain.city.util.key
import com.aminography.model.city.City
import com.aminography.test.CoroutineTest
import com.aminography.test.testCities
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityRepositoryImplTest : CoroutineTest() {

    private val cityDataSource: CityDataSource = mockk()
    private val pagingFactory: CityPagingFactory = mockk()

    @Test
    fun `loading distinct cities should insert all of them into tree`() = runBlockingTest {
        // Given
        val expected = testCities
        coEvery { cityDataSource.loadCityListConcurrently() } returns testCities

        val cityRepository = CityRepositoryImpl(cityDataSource, pagingFactory)

        // When
        cityRepository.loadCities()
        val result = cityRepository.cache?.values.orEmpty()

        // Then
        assertEquals(result.size, expected.size)

        coVerifySequence {
            cityDataSource.loadCityListConcurrently()
        }
    }

    @Test
    fun `loading cities should drop duplicated elements in tree`() = runBlockingTest {
        // Given
        val expected = listOf(testCities[0])
        coEvery { cityDataSource.loadCityListConcurrently() } returns listOf(
            testCities[0],
            testCities[0].copy()
        )

        val cityRepository = CityRepositoryImpl(cityDataSource, pagingFactory)

        // When
        cityRepository.loadCities()
        val result = cityRepository.cache?.values.orEmpty()

        // Then
        assertEquals(result, expected)

        coVerifySequence {
            cityDataSource.loadCityListConcurrently()
        }
    }

    @Test
    fun `search cities should return a empty PagingData if cache is null`() = runBlockingTest {
        // Given
        val query = "test"
        val expected = PagingData.empty<City>()

        val cityRepository = CityRepositoryImpl(cityDataSource, pagingFactory)

        // When
        val result = cityRepository.searchCities(query).toList()

        // Then
        assertEquals(result.size, 1)
        Assertions.assertSame(result[0], expected)
    }

    @Test
    fun `search cities should return flow of PagingData when cache isn't null`() = runBlockingTest {
        // Given
        val query = testCities[0].key
        val expected = PagingData.from(listOf(testCities[0]))

        coEvery { cityDataSource.loadCityListConcurrently() } returns testCities
        every { pagingFactory.createPagingDataFlow(any(), query) } returns flowOf(expected)

        val cityRepository = CityRepositoryImpl(cityDataSource, pagingFactory)

        // When
        cityRepository.loadCities()
        val result = cityRepository.searchCities(query).toList()

        // Then
        assertEquals(result.size, 1)
        Assertions.assertSame(result[0], expected)

        coVerifySequence {
            cityDataSource.loadCityListConcurrently()
            pagingFactory.createPagingDataFlow(any(), query)
        }
    }
}