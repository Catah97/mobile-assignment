package com.example.rocketapp.rocket.list.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketapp.rocket.list.adapter.RocketItem
import com.example.rocketapp.rocket.list.adapter.RocketListAdapter

@BindingAdapter("bindRocketItemList")
fun RecyclerView.bindRocketItemList(list: List<RocketItem>?) {
    val adapter = adapter as? RocketListAdapter
    adapter?.submitList(list)
}
