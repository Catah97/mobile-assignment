package com.example.rocketapp.rocket.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rocketapp.R
import com.example.rocketapp.databinding.FragmentRocketListBinding
import com.example.rocketapp.rocket.detail.RocketDetailFragment
import com.example.rocketapp.rocket.list.adapter.RocketListAdapter
import com.example.rocketapp.tools.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment : BaseFragment<
    FragmentRocketListBinding,
    RocketListViewModel>(RocketListViewModel::class) {

    override val bindingInflater = { layoutInflater: LayoutInflater, parent: ViewGroup? ->
        FragmentRocketListBinding.inflate(layoutInflater, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRocketListRecyclerView()
    }

    private fun setRocketListRecyclerView() {
        val rocketListAdapter = RocketListAdapter()
        binding.recyclerView.apply {
            adapter = rocketListAdapter
            setHasFixedSize(true)
        }
        rocketListAdapter.setOnItemClickListener { _, rocket ->
            val arguments = RocketDetailFragment.createArguments(rocket.id)
            navController.navigate(R.id.action_rocket_list_to_rocket_detail, arguments)
        }
    }

    companion object {
        private const val TAG = "RocketListFragment"
    }
}
