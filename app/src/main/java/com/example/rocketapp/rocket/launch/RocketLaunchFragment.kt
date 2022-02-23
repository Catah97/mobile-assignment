package com.example.rocketapp.rocket.launch

import android.content.Context
import android.hardware.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rocketapp.databinding.FragmentRocketLaunchBinding
import com.example.rocketapp.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RocketLaunchFragment: BaseFragment<
        FragmentRocketLaunchBinding,
        RocketLaunchViewModel>(RocketLaunchViewModel::class), SensorEventListener {

    private val rocketAnimationHelp = RocketAnimationHelp()

    private val sensorManager by lazy {
        requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override val bindingInflater = { layoutInflater: LayoutInflater, parent: ViewGroup? ->
        FragmentRocketLaunchBinding.inflate(layoutInflater, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgLaunchingRocket.setOnClickListener {
            viewModel.launchRocket()
        }
        setUpObservers()
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.launchStatusData.collectLatest {
                    when (it) {
                        RocketLaunchStatus.IDLE -> {
                        }
                        RocketLaunchStatus.LAUNCHING -> {
                            onLaunchStarted()
                            unregisterSensor()
                        }
                        RocketLaunchStatus.DONE -> {
                            unregisterSensor()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerSensor()
    }

    override fun onPause() {
        super.onPause()
        unregisterSensor()
    }

    private fun registerSensor() {
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d(TAG, "onSensorChanged")
        val values = event.values
        val value = values[0]
        Log.d(TAG, "onSensorChanged: $value")
        if (value > 2) {
            viewModel.launchRocket()
        }
    }

    private fun onLaunchStarted() {
        val rocketImageView = binding.imgLaunchingRocket
        rocketAnimationHelp.startRocketLaunchAnimation(rocketImageView) {
            viewModel.launchDone()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //Ignored
    }

    companion object {
        private const val TAG = "RocketLaunchFragment"
    }
}
