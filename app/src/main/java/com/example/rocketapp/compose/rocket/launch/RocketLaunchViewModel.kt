package com.example.rocketapp.compose.rocket.launch

import androidx.lifecycle.viewModelScope
import com.example.rocketapp.rocket.launch.RocketLaunchStatus
import com.example.rocketapp.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketLaunchViewModel @Inject constructor() : BaseViewModel() {

    val launchStatusData = MutableStateFlow(RocketLaunchStatus.IDLE)

    fun launchRocket() {
        if (launchStatusData.value == RocketLaunchStatus.IDLE) {
            viewModelScope.launch {
                launchStatusData.value = RocketLaunchStatus.LAUNCHING
            }
        }
    }

    fun cancelIfLaunching() {
        if (launchStatusData.value == RocketLaunchStatus.LAUNCHING) {
            viewModelScope.launch {
                launchStatusData.value = RocketLaunchStatus.IDLE
            }
        }
    }

    fun launchDone() {
        viewModelScope.launch {
            launchStatusData.value = RocketLaunchStatus.DONE
        }
    }
}
