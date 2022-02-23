package com.example.rocketapp.rocket.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rocketapp.rocket.repository.SpaceXRocketRepository
import com.example.rocketapp.rocket.repository.model.Rocket
import com.example.rocketapp.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val spaceXRocketRepository: SpaceXRocketRepository
) : BaseViewModel() {

    val rocketData by lazy {
        spaceXRocketRepository.getRocketData()
            .mapNotNull { result ->
                val rockets = result?.getOrNull()
                rockets?.firstOrNull { it.id == rocketId }
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, null)
    }

    private var rocketId: Int? = null

    fun loadDetail(rocketId: Int) {
        this.rocketId = rocketId
    }

    companion object {
        private const val TAG = "RocketDetailViewModel"
    }

}