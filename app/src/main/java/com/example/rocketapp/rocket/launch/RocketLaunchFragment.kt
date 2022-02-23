package com.example.rocketapp.rocket.launch

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.rocketapp.databinding.FragmentRocketLaunchBinding
import com.example.rocketapp.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketLaunchFragment : BaseFragment<
    FragmentRocketLaunchBinding,
    RocketLaunchViewModel>(RocketLaunchViewModel::class) {

    override val bindingInflater = { layoutInflater: LayoutInflater, parent: ViewGroup? ->
        FragmentRocketLaunchBinding.inflate(layoutInflater, parent, false)
    }
}
