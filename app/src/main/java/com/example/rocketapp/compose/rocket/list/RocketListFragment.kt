package com.example.rocketapp.compose.rocket.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import com.example.rocketapp.R
import com.example.rocketapp.compose.rocket.base.ComposeBaseFragment
import com.example.rocketapp.compose.rocket.detail.RocketDetailFragment
import com.example.rocketapp.rocket.list.RocketListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment : ComposeBaseFragment() {

    val rocketListViewModel: RocketListViewModel by viewModels()

    @Composable
    override fun FragmentContent() {
        val rocketList = rocketListViewModel.rocketItemsData.collectAsState().value
        RocketListScreen(rocketList) { rocketItem ->
            val arguments = RocketDetailFragment.createArguments(rocketItem.id)
            navController.navigate(R.id.action_rocket_list_to_rocket_detail, arguments)
        }
    }
}
