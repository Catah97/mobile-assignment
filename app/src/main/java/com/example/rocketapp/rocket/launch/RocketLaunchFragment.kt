package com.example.rocketapp.rocket.launch

import android.content.Context
import android.hardware.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.contentValuesOf
import androidx.fragment.app.viewModels
import com.example.rocketapp.R
import com.example.rocketapp.databinding.FragmentRocketLaunchBinding
import com.example.rocketapp.databinding.FragmentRocketListBinding
import com.example.rocketapp.rocket.list.RocketListViewModel
import com.example.rocketapp.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketLaunchFragment: BaseFragment<
        FragmentRocketLaunchBinding,
        RocketLaunchViewModel>(RocketLaunchViewModel::class), SensorEventListener {

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
    }

    override fun onResume() {
        super.onResume()
        registerSensor()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    private fun registerSensor() {
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
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
        //Ignored
    }

    companion object {
        private const val TAG = "RocketLaunchFragment"
    }
}