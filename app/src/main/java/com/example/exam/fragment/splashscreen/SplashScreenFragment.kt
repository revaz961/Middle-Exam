package com.example.exam.fragment.splashscreen

import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.databinding.FragmentSplashScreenBinding
import com.example.exam.fragment.BaseFragment
import com.example.exam.sharedpreferences.SharedPreferences


class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>(
    FragmentSplashScreenBinding::inflate,
    SplashScreenViewModel::class.java
) {

    override fun start() {
        init()
        observes()
    }

    private fun init() {
        checkAuthorize()
    }

    private fun checkAuthorize() {
        val user = SharedPreferences.getUser()
        if (user != null)
            viewModel.authMe(requireActivity(),user.first, user.second)
        else
        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
    }

    private fun observes() {
        viewModel.splashLiveData.observe(this,{
            if (it)
                findNavController().navigate(R.id.action_splashScreenFragment_to_mainFragment)
            else
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
        })
    }
}