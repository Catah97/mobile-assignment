package com.example.rocketapp.compose.rocket.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rocketapp.rocket.detail.RocketDetailViewModel
import com.example.rocketapp.rocket.repository.model.Rocket


@Composable
fun RocketDetailScreen(rocket: Rocket?) {
    if (rocket != null) {
        Text(rocket.name)
    } else {
        Text("Loading")
    }
}