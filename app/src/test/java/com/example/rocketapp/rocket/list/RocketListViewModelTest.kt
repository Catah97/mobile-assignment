package com.example.rocketapp.rocket.list

import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.rocket.repository.SpaceXRocketTestRepository
import com.example.rocketapp.rocket.repository.TestRocketRepository
import com.example.rocketapp.rocket.repository.createRocketRepository
import com.example.rocketapp.rocket.repository.model.Rocket
import com.example.rocketapp.rocket.repository.model.info.RocketHeight
import com.example.rocketapp.rocket.tools.TestRocketBuilder
import org.junit.Test
import java.util.*

class RocketListViewModelTest {

    @Test
    fun testRocketListLoad() {
        val repository = createRocketRepository()
        val viewModel = RocketListViewModel(repository)
        viewModel.loadRockets()
        //Waiting for coroutine to load data
        Thread.sleep(500)
        val repositoryList = repository.getRocketList()
        val viewModelList = viewModel.rocketItemsData.value
        assert(viewModelList.size == repositoryList.size)
        viewModelList.forEachIndexed { index, rocketItem ->
            val repositoryItem = repositoryList[index]
            rocketItem.assert(repositoryItem)
        }
    }

    private fun RocketItem.assert(rocket: Rocket) {
        assert(id == rocket.id) { "Diff item id: $id rocket id: ${rocket.id}" }
        assert(name == rocket.name) { "Diff item name: $name rocket name: ${rocket.name}" }
        assert(firstFlight == rocket.firstFlight) { "Diff item firstFlight: $firstFlight rocket firstFlight: ${rocket.firstFlight}" }

    }

}