package com.example.rocketapp.rocket.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.rocket.repository.SpaceXRocketRepository
import com.example.rocketapp.rocket.repository.model.Rocket
import com.example.rocketapp.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val spaceXRocketRepository: SpaceXRocketRepository
) : BaseViewModel() {

    val rocketItemsData = MutableStateFlow<List<RocketItem>>(emptyList())

    init {
        loadRockets()
    }

    fun loadRockets() {
        viewModelScope.launch {
            Log.d(TAG, "loadRockets request for $spaceXRocketRepository")
            val result = spaceXRocketRepository.loadRocketData()
            val rockets = result.getOrNull() ?: emptyList()
            val rocketItems = rockets.map {
                RocketItem(it.id, it.name, it.firstFlight)
            }
            rocketItemsData.value = rocketItems
        }
    }

    companion object {
        private const val TAG = "RocketListViewModel"
    }

}