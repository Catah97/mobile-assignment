package com.example.rocketapp.rocket.list

import androidx.lifecycle.*
import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.rocket.repository.SpaceXRocketRepository
import com.example.rocketapp.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val spaceXRocketRepository: SpaceXRocketRepository
) : BaseViewModel() {

    val rocketItemsData = spaceXRocketRepository.getRocketData().map { result ->
        val rockets = result?.getOrNull() ?: emptyList()
        rockets.map {
            RocketItem(it.id, it.name, it.firstFlight)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        loadRockets()
    }

    fun loadRockets() {
        viewModelScope.launch {
            spaceXRocketRepository.loadRocketData()
        }
    }

    companion object {
        private const val TAG = "RocketListViewModel"
    }
}
