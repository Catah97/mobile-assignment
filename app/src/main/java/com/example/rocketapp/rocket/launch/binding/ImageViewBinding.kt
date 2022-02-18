package com.example.rocketapp.rocket.launch.binding

import android.content.res.Resources
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.rocketapp.R
import com.example.rocketapp.rocket.launch.RocketLaunchStatus
import com.example.rocketapp.rocket.launch.RocketLaunchStatus.*


@BindingAdapter("setRocketLaunchStatusImage")
fun ImageView.setRocketLaunchStatusImage(status: RocketLaunchStatus) {
    val icon = when (status) {
        IDLE -> R.drawable.ic_rocket_idle
        LAUNCHING, DONE -> R.drawable.ic_rocket_flying
    }
    val drawable = ContextCompat.getDrawable(context, icon)
    setImageDrawable(drawable)
    if (status == LAUNCHING) {
        val displayMetrics = Resources.getSystem().displayMetrics
        val displayHeight: Int = displayMetrics.heightPixels
        val animationDistance: Float = -(displayHeight + height).toFloat()
        this
            .animate()
            .translationY(animationDistance)
            .setDuration(3000)
            .setInterpolator(AccelerateInterpolator())
            .start()
    }
}
