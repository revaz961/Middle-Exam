package com.example.exam.fragment.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exam.sharedpreferences.SharedPreferences
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private val _loginLiveData = MutableLiveData<Boolean>()
    val loginLiveData:LiveData<Boolean> = _loginLiveData

    fun login(activity: Activity, email: String, password: String,remember:Boolean){

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    if (remember)
                        SharedPreferences.setUser(email, password)
                    _loginLiveData.postValue(true)
                } else {
                    _loginLiveData.postValue(false)
                }
            }
    }
}