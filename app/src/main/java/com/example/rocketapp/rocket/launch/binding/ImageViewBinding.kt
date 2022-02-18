package com.example.rocketapp.rocket.launch.binding

import android.animation.ObjectAnimator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
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
        ObjectAnimator.ofFloat(this, "translationY", 0F).apply {
            duration = 1000
            start()
        }

    }
}
