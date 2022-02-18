package com.example.rocketapp.rocket.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rocketapp.R
import com.example.rocketapp.databinding.FragmentRocketDetailBinding
import com.example.rocketapp.databinding.FragmentRocketListBinding
import com.example.rocketapp.rocket.detail.adapter.RocketPhotosAdapter
import com.example.rocketapp.rocket.launch.RocketLaunchViewModel
import com.example.rocketapp.rocket.list.RocketListFragment
import com.example.rocketapp.rocket.list.RocketListViewModel
import com.example.rocketapp.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RocketDetailFragment: BaseFragment<
        FragmentRocketDetailBinding,
        RocketDetailViewModel>(RocketDetailViewModel::class) {

    override val bindingInflater = { layoutInflater: LayoutInflater, parent: ViewGroup? ->
        FragmentRocketDetailBinding.inflate(layoutInflater, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPhotoRecyclerView()
        loadRocket()
        observerRocket()
    }

    private fun setPhotoRecyclerView() {
        val photosAdapter = RocketPhotosAdapter()
        binding.rocketPhotosRecyclerView.apply {
            adapter = photosAdapter
        }
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