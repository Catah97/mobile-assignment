package com.example.rocketapp.compose.rocket.detail

import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rocketapp.R
import com.example.rocketapp.compose.rocket.base.ComposeBaseFragment
import com.example.rocketapp.rocket.detail.RocketDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RocketDetailFragment : ComposeBaseFragment() {

    val viewModel: RocketDetailViewModel by viewModels()

    @Composable
    override fun FragmentContent() {
        val rocket = viewModel.rocketData.collectAsState().value
        RocketDetailScreen(rocket = rocket)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.launch) {
            launchRocket()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun launchRocket() {
        navController.navigate(R.id.action_rocket_detail_to_rocket_launch)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        loadRocket()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerRocket()
    }

    private fun loadRocket() {
        val defaultValue = -1
        val rocketId = arguments?.getInt(ROCKET_ID_KEY) ?: defaultValue
        if (rocketId == defaultValue) {
            return
        }
        viewModel.loadDetail(rocketId)
    }

    private fun observerRocket() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rocketData.collect { rocket ->
                    val title = rocket?.name ?: getString(R.string.loading)
                    setActionBarTitle(title)
                }
            }
        }
    }

    private fun setActionBarTitle(newTitle: String) {
        setActionBar {
            title = newTitle
        }
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
