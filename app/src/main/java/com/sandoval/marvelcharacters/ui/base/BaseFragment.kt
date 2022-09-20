package com.sandoval.marvelcharacters.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sandoval.marvelcharacters.data.network.NetworkHelper

abstract class BaseFragment<DB : ViewDataBinding>(
    private val _bindingInflater: (inflater: LayoutInflater) -> DB
) : Fragment() {

    private var _binding: DB? = null
    val binding: DB get() = _binding as DB

    private var networkHelper: NetworkHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = _bindingInflater.invoke(inflater)

        networkHelper = NetworkHelper(requireContext())
        when {
            networkHelper!!.isNetworkConnected() -> {
                initViews()
                initViewModels()
            }
            else -> showNoInternetConnection()
        }

        when (_binding) {
            null -> throw IllegalArgumentException("Binding cannot be null")
            else -> return binding.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initViews()
    abstract fun initViewModels()
    open fun showNoInternetConnection() {}
}