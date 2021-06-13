package com.example.exam.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.exam.R


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init(){
        requireView().findViewById<Button>(R.id.btnLogin).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}