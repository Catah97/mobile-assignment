package com.example.rocketapp.compose.rocket.launch

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.example.rocketapp.compose.rocket.base.ComposeBaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketLaunchFragment : ComposeBaseFragment(), SensorEventListener {

    val viewModel: RocketLaunchViewModel by viewModels()

    private val sensorManager by lazy {
        requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @Composable
    override fun FragmentContent() {
        LaunchScreen(viewModel)
    }

    override fun onResume() {
        super.onResume()
        registerSensor()
    }

    override fun onPause() {
        super.onPause()
        viewModel.cancelIfLaunching()
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

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Ignored
    }

    companion object {
        private const val TAG = "RocketLaunchFragment"
    }
}
