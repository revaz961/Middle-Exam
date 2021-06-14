package com.example.exam

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class SplashScreenFragment : Fragment() {

    private val sharedPreference:SharedPreferenceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private fun init(){
        sharedPreference.init(requireActivity())
        checkAuthorize()
    }

    private fun checkAuthorize(){
        val user = sharedPreference.getUser()
        if(user != null)
            authMe(user.first,user.second)
        else
            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
    }

    private fun authMe(email:String,password:String){
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email.trim(), password.trim())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_mainFragment)
                } else {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }
            }
    }
}