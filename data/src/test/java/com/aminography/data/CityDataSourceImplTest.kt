package com.aminography.data

import com.aminography.data.city.datasource.CityDataSourceImpl
import com.aminography.data.city.datasource.JsonRetriever
import com.aminography.data.city.datasource.LineCounter
import com.aminography.data.city.datasource.adapter.Inserter
import com.aminography.model.city.City
import com.aminography.test.CoroutineTest
import com.aminography.test.testCities
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityDataSourceImplTest : CoroutineTest() {

    private val jsonRetriever: JsonRetriever = mockk()
    private val lineCounter: LineCounter = mockk()
    private val fileName: String = "test"
    private val concurrencyLevel: Int = 2

    @Test
    fun `load list of cities`() = runBlockingTest {
        // Given
        val expected = testCities

        val inserterSlot: CapturingSlot<Inserter<City>> = slot()
        coEvery { jsonRetriever.readTo(fileName, capture(inserterSlot)) } answers {
            testCities.forEach { inserterSlot.captured.insert(it) }
        }

        val cityDataSource = CityDataSourceImpl(
            jsonRetriever,
            lineCounter,
            fileName,
            concurrencyLevel,
            testDispatcher,
            testDispatcher
        )

        // When
        val result = cityDataSource.loadCityList()

        // Then
        Assertions.assertEquals(result, expected)

        coVerifySequence {
            jsonRetriever.readTo(fileName, inserterSlot.captured)
        }
    }

    @Test
    fun `load tree of cities`() = runBlockingTest {
        // Given
        val expected = testCities

        val inserterSlot: CapturingSlot<Inserter<City>> = slot()
        coEvery { jsonRetriever.readTo(fileName, capture(inserterSlot)) } answers {
            testCities.forEach { inserterSlot.captured.insert(it) }
        }

        val cityDataSource = CityDataSourceImpl(
            jsonRetriever,
            lineCounter,
            fileName,
            concurrencyLevel,
            testDispatcher,
            testDispatcher
        )

        // When
        val result = cityDataSource.loadCityRadixTree().toList()

        // Then
        Assertions.assertEquals(result, expected)

        coVerifySequence {
            jsonRetriever.readTo(fileName, inserterSlot.captured)
        }
    }


    @Test
    fun `load list of cities concurrently`() = runBlockingTest {
        // Given
        val expected = testCities

        val inserterSlot: CapturingSlot<Inserter<City>> = slot()
        val offsetSlot: CapturingSlot<Int> = slot()
        val limitSlot: CapturingSlot<Int> = slot()

        coEvery {
            jsonRetriever.readTo(
                fileName,
                capture(inserterSlot),
                capture(offsetSlot),
                capture(limitSlot)
            )
        } answers {
            val offset = offsetSlot.captured
            val limit = limitSlot.captured

            for (i in offset until offset + limit) {
                inserterSlot.captured.insert(testCities[i])
            }
        }

        coEvery { lineCounter.count(fileName) } returns testCities.size

        val cityDataSource = CityDataSourceImpl(
            jsonRetriever,
            lineCounter,
            fileName,
            concurrencyLevel,
            testDispatcher,
            testDispatcher
        )

        // When
        val result = cityDataSource.loadCityListConcurrently()

        // Then
        Assertions.assertEquals(result, expected)

        coVerifySequence {
            lineCounter.count(fileName)
            jsonRetriever.readTo(fileName, any(), any(), any())
            jsonRetriever.readTo(fileName, any(), any(), any())
        }
    }
}