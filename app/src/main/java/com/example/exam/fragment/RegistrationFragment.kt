package com.example.exam.fragment

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import com.example.exam.R
import com.example.exam.databinding.FragmentLoginBinding
import com.example.exam.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class RegistrationFragment : Fragment() {

    private var binding: FragmentRegistrationBinding? = null
    private var email = ObservableField<String>()
    private var password = ObservableField<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentRegistrationBinding.inflate(layoutInflater)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        binding!!.email = email
        binding!!.password = password
        binding!!.btnSignUp.setOnClickListener {
            findNavController().navigateUp()
        }

        binding!!.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        d("emailpassword","${email.get()!!.trim()} ${password.get()!!.trim()}")
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email.get()!!.trim(), password.get()!!.trim())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigateUp()
                } else {
                    Snackbar.make(
                        requireView(), "Authentication failed.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
    }
}