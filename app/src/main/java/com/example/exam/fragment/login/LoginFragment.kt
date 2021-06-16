package com.example.exam.fragment.login

import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.databinding.FragmentLoginBinding
import com.example.exam.extension.snackbar
import com.example.exam.fragment.BaseFragment
import com.google.android.material.snackbar.Snackbar


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate,
    LoginViewModel::class.java
) {
    private var email = ObservableField<String>()
    private var password = ObservableField<String>()

    override fun start() {
        init()
        observes()
    }

    private fun init() {
        binding.email = email
        binding.password = password
        with(binding) {
            btnLogin.setOnClickListener {
                login()
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }

    private fun login() {
        binding.btnLogin.isClickable = false
        if (!email.get().isNullOrBlank() && !password.get().isNullOrBlank())
            viewModel.login(
                requireActivity(),
                email.get()!!.trim(),
                password.get()!!.trim(),
                binding.checkRemember.isChecked
            )
        else {
            requireView().snackbar(getString(R.string.login_failed), Snackbar.LENGTH_LONG)
            binding.btnLogin.isClickable = true
        }
    }

    private fun observes() {
        viewModel.loginLiveData.observe(this, {
            if (it)
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            else {
                requireView().snackbar(getString(R.string.login_failed), Snackbar.LENGTH_LONG)
                binding.btnLogin.isClickable = true
            }
        })
    }
}