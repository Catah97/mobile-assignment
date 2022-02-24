package com.example.rocketapp.compose.rocket.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rocketapp.compose.rocket.base.ComposeBaseFragment
import com.example.rocketapp.rocket.detail.RocketDetailViewModel
import com.example.rocketapp.rocket.list.RocketListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketLaunchFragment: ComposeBaseFragment() {

    @Composable
    override fun FragmentContent() {
        Text("Launch view")
    }


}