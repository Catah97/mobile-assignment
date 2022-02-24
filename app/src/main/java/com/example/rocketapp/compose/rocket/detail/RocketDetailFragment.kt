package com.example.rocketapp.compose.rocket.detail

import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rocketapp.R
import com.example.rocketapp.compose.rocket.base.ComposeBaseFragment
import com.example.rocketapp.rocket.detail.RocketDetailFragment
import com.example.rocketapp.rocket.detail.RocketDetailViewModel
import com.example.rocketapp.rocket.list.RocketListViewModel
import com.example.rocketapp.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailFragment: ComposeBaseFragment() {

    val viewModel: RocketDetailViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.launch) {
            //launchRocket()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        loadRocket()
    }

    @Composable
    override fun FragmentContent() {
        val rocket = viewModel.rocketData.collectAsState().value
        RocketDetailScreen(rocket = rocket)
    }

    private fun loadRocket() {
        val defaultValue = -1
        val rocketId = arguments?.getInt(ROCKET_ID_KEY) ?: defaultValue
        if (rocketId == defaultValue) {
            return
        }
        viewModel.loadDetail(rocketId)
    }

    companion object {
        private const val TAG = "RocketDetailFragment"

        private const val ROCKET_ID_KEY = "RocketIdKey"

        fun createArguments(rocketId: Int): Bundle {
            val bundle = Bundle()
            bundle.apply {
                putInt(ROCKET_ID_KEY, rocketId)
            }
            return bundle
        }
    }
}