package com.example.exam.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private var email = ObservableField<String>()
    private var password = ObservableField<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentLoginBinding.inflate(layoutInflater)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        binding!!.email = email
        binding!!.password = password
        binding!!.btnLogin.setOnClickListener {
            binding!!.btnLogin.isClickable = false
            login(email.get()!!.trim(), password.get()!!.trim())
        }
        binding!!.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun login(email: String, password: String) {

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    binding!!.btnLogin.isClickable = true
                    Snackbar.make(requireView(), "Login Faild", Snackbar.LENGTH_LONG).show()
                }
            }
    }

}