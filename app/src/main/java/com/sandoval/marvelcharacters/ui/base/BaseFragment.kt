package com.sandoval.marvelcharacters.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding>(
    private val _bindingInflater: (inflater: LayoutInflater) -> DB
) : Fragment() {

    private var _binding: DB? = null
    val binding: DB get() = _binding as DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = _bindingInflater.invoke(inflater)
        initViews()
        initViewModels()
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
}