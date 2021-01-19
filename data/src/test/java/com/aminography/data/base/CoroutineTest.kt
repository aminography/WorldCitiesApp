package com.aminography.data.base

import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

/**
 * @author aminography
 */
@ExperimentalCoroutinesApi
internal open class CoroutineTest {

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