package com.example.rocketapp.rocket.launch.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.rocketapp.R
import com.example.rocketapp.rocket.launch.RocketLaunchStatus
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.DONE
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.IDLE
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.LAUNCHING

@BindingAdapter("setRocketLaunchStatusText")
fun TextView.setRocketLaunchStatusText(status: RocketLaunchStatus) {
    val textValue = when (status) {
        IDLE -> context.getString(R.string.launch_rocket_info)
        LAUNCHING, DONE -> context.getString(R.string.launch_successful)
    }
    text = textValue
}
