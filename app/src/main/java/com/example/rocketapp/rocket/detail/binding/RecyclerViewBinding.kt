package com.example.rocketapp.rocket.detail.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketapp.rocket.detail.adapter.RocketPhotosAdapter
import com.example.rocketapp.rocket.repository.model.Rocket

@BindingAdapter("bindRocketPhotos")
fun RecyclerView.bindRocketPhotos(rocket: Rocket?) {
    val photosUrl = rocket?.image ?: emptyList()
    val adapter = adapter as? RocketPhotosAdapter
    adapter?.submitList(photosUrl)
}
