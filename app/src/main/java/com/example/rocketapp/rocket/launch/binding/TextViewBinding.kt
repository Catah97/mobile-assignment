package com.example.rocketapp.rocket.launch.binding

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.rocketapp.R
import com.example.rocketapp.rocket.launch.RocketLaunchStatus
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.*
import kotlin.math.roundToInt

@BindingAdapter("setRocketLaunchStatusText")
fun TextView.setRocketLaunchStatusText(status: RocketLaunchStatus) {
    val textValue = when (status) {
        IDLE -> context.getString(R.string.launch_rocket_info)
        LAUNCHING, DONE -> context.getString(R.string.launch_successful)
    }
    text = textValue
}
