package com.example.rocketapp.rocket.list.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketapp.rocket.detail.adapter.RocketPhotosAdapter
import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.rocket.list.adapter.RocketListAdapter
import com.example.rocketapp.rocket.repository.model.Rocket


@BindingAdapter("bindRocketItemList")
fun RecyclerView.bindRocketItemList(list: List<RocketItem>?) {
    val adapter = adapter as? RocketListAdapter
    adapter?.submitList(list)
}