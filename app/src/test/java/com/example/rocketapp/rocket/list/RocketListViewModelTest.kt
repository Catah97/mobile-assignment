package com.example.rocketapp.rocket.list

import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.rocket.repository.createErrorRocketRepository
import com.example.rocketapp.rocket.repository.createRocketRepository
import com.example.rocketapp.rocket.repository.model.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RocketListViewModelTest {

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
        val viewModel = RocketListViewModel(repository)
        viewModel.loadRockets()
        // Waiting for coroutine to load data
        Thread.sleep(500)
        val repositoryList = repository.getRocketList()
        val viewModelList = viewModel.rocketItemsData.value
        assert(viewModelList.size == repositoryList.size) { "Diff item size: ${viewModelList.size} repository size: ${repositoryList.size}" }
        viewModelList.forEachIndexed { index, rocketItem ->
            val repositoryItem = repositoryList[index]
            rocketItem.assert(repositoryItem)
        }
    }

    @Test
    fun testRocketListLoadFailed() {
        val repository = createErrorRocketRepository()
        val viewModel = RocketListViewModel(repository)
        viewModel.loadRockets()
        // Waiting for coroutine to load data
        Thread.sleep(500)
        val viewModelList = viewModel.rocketItemsData.value
        assert(viewModelList.isEmpty()) { "ViewModelList is not empty" }
    }

    private fun RocketItem.assert(rocket: Rocket) {
        assert(id == rocket.id) { "Diff item id: $id rocket id: ${rocket.id}" }
        assert(name == rocket.name) { "Diff item name: $name rocket name: ${rocket.name}" }
        assert(firstFlight == rocket.firstFlight) { "Diff item firstFlight: $firstFlight rocket firstFlight: ${rocket.firstFlight}" }
    }
}
