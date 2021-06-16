package com.example.exam.fragment.registration

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegistrationViewModel : ViewModel() {
    private val _registrationLiveData = MutableLiveData<Boolean>()
    val registrationLiveData: LiveData<Boolean> = _registrationLiveData

    fun register(activity:Activity,email: String, password: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    _registrationLiveData.postValue(true)
                } else {
                   _registrationLiveData.postValue(false)
                }
            }
    }
}