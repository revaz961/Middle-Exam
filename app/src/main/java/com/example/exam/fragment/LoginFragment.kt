package com.example.exam.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.ObservableField
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
            login()
        }
        binding!!.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun login() {
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email.get()!!.trim(), password.get()!!.trim())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    Snackbar.make(
                        requireView(), "Authentication failed.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
    }
}