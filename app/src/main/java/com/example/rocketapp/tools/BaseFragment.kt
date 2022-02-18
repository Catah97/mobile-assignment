package com.example.rocketapp.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.rocketapp.BR
import javax.inject.Inject
import kotlin.reflect.KClass


abstract class BaseFragment<VB: ViewDataBinding, VM : BaseViewModel>(
    private val viewModelClass: KClass<VM>
) : Fragment() {

    protected val viewModel by lazy {
        ViewModelProvider(this).get(viewModelClass.java)
    }

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    //TODO pozdeji prepisu na data binding
    abstract val bindingInflater: (LayoutInflater, ViewGroup?) -> VB


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container)
        _binding?.lifecycleOwner = viewLifecycleOwner
        _binding?.setVariable(getViewModelId(), viewModel)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected val navController by lazy {
        findNavController()
    }

    protected fun setActionBar(block: ActionBar.() -> Unit) {
        val activity = activity as AppCompatActivity
        val actionBar = activity.supportActionBar
        actionBar?.block()
    }

    protected open fun getViewModelId() = BR.vm

}