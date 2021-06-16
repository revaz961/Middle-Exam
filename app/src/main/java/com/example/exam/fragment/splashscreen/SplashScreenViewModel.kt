package com.example.exam.fragment.splashscreen

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SplashScreenViewModel: ViewModel() {

    private val _splashLiveData = MutableLiveData<Boolean>()
    val splashLiveData:LiveData<Boolean> = _splashLiveData

    fun authMe(activity:Activity,email:String,password:String){
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email.trim(), password.trim())
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    _splashLiveData.postValue(true)
                } else {
                    _splashLiveData.postValue(false)
                }
            }
    }
}