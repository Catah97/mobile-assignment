package com.example.rocketapp.rocket.detail.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.rocketapp.rocket.repository.model.Rocket

@BindingAdapter("visibilityByRocketPhotos")
fun View.visibilityByRocketPhotos(rocket: Rocket?) {
    val photosUrl = rocket?.image
    visibility = if (photosUrl.isNullOrEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
