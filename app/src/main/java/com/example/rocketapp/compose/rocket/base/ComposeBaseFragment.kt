package com.example.rocketapp.compose.rocket.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class ComposeBaseFragment : Fragment() {

    @Composable
    abstract fun FragmentContent()

    protected val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext()).apply {
            setContent {
                FragmentContent()
            }
        }
        return view
    }

    protected fun setActionBar(block: ActionBar.() -> Unit) {
        val activity = activity as AppCompatActivity
        val actionBar = activity.supportActionBar
        actionBar?.block()
    }
}
