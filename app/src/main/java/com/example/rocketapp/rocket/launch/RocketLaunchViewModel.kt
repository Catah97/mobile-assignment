package com.example.rocketapp.rocket.launch

import androidx.lifecycle.viewModelScope
import com.example.rocketapp.tools.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketLaunchViewModel @Inject constructor(
) : BaseViewModel() {

    val launchStatusData = MutableStateFlow(RocketLaunchStatus.IDLE)

    fun launchRocket() {
        viewModelScope.launch {
            launchStatusData.value = RocketLaunchStatus.LAUNCHING
        }
    }

    fun launchDone() {
        viewModelScope.launch {
            launchStatusData.value = RocketLaunchStatus.DONE
        }
    }

}