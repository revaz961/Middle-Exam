package com.example.exam.fragment.registration

import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.databinding.FragmentRegistrationBinding
import com.example.exam.extension.snackbar
import com.example.exam.fragment.BaseFragment
import com.google.android.material.snackbar.Snackbar


class RegistrationFragment : BaseFragment<FragmentRegistrationBinding, RegistrationViewModel>(
    FragmentRegistrationBinding::inflate,
    RegistrationViewModel::class.java
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
            btnSignUp.setOnClickListener {
                findNavController().navigateUp()
            }

            btnRegister.setOnClickListener {
                btnRegister.isClickable = false
                register()
            }
        }

    }

    private fun register() {
        if (!email.get().isNullOrBlank() && !password.get().isNullOrBlank())
            viewModel.register(
                requireActivity(),
                email.get()!!.trim(),
                password.get()!!.trim()
            )
        else {
            requireView().snackbar(
                getString(R.string.incorect_registration),
                Snackbar.LENGTH_LONG
            )
            binding.btnRegister.isClickable = true
        }
    }

    private fun observes() {
        viewModel.registrationLiveData.observe(this, {
            if (it)
                findNavController().navigateUp()
            else {
                requireView().snackbar(
                    getString(R.string.incorect_registration),
                    Snackbar.LENGTH_LONG
                )
                binding.btnRegister.isClickable = true
            }
        })
    }
}