package com.aminography.test

import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 * Base class for unit-testing suspend functions by providing [TestCoroutineDispatcher] as the main
 * dispatcher.
 *
 * @author aminography
 */
@ExperimentalCoroutinesApi
open class CoroutineTest {

    protected val testDispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        clearAllMocks()
    }

    @AfterEach
    fun afterEach() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        runBlockingTest(testDispatcher, block)
    }
}