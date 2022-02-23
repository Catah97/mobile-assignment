package com.example.rocketapp.rocket.launch.binding

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.rocketapp.R
import com.example.rocketapp.rocket.launch.RocketLaunchStatus
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.*

@BindingAdapter("setRocketLaunchStatusImage")
fun ImageView.setRocketLaunchStatusImage(status: RocketLaunchStatus) {
    visibility = when (status) {
        IDLE -> {
            val icon = R.drawable.ic_rocket_idle
            setRocketIcon(icon)
            View.VISIBLE
        }
        LAUNCHING -> {
            val icon = R.drawable.ic_rocket_flying
            setRocketIcon(icon)
            View.VISIBLE
        }
        DONE -> {
            View.GONE
        }
    }
}

private fun ImageView.setRocketIcon(@DrawableRes icon: Int) {
    val drawable = ContextCompat.getDrawable(context, icon)
    setImageDrawable(drawable)
}
