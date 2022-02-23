package com.example.rocketapp.rocket.detail

import com.example.rocketapp.rocket.repository.createRocketRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RocketDetailFragmentTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testRocketListLoad() {
        val repository = createRocketRepository()
        val detailItem = repository.getRocketList().first()
        val viewModel = RocketDetailViewModel(repository)
        viewModel.loadDetail(detailItem.id)
        runBlocking {
            repository.loadRocketData()
            viewModel.rocketData
                .take(2)
                .collectIndexed { index, value ->
                if (index == 0) {
                    assert(value == null)
                } else if (index == 1) {
                    assert(detailItem == value)
                }
            }
        }
    }
}