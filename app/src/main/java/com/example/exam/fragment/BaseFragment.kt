package com.example.exam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater,ViewGroup?,Boolean) -> T

abstract class BaseFragment<VB:ViewBinding,VM:ViewModel>(private val inflate:Inflate<VB>,private val viewModelClass:Class<VM>,private val isShared:Boolean = false):Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    protected val viewModel:VM by lazy {
        if (isShared) {
            ViewModelProvider(requireActivity()).get(
                viewModelClass
            )
        } else {
            ViewModelProvider(this).get(viewModelClass)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(_binding == null) {
            _binding = inflate(inflater, container, false)
            start()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun start()
}