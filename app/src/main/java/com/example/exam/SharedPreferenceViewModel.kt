package com.example.exam

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class SharedPreferenceViewModel : ViewModel() {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("local_storage", MODE_PRIVATE)
    }

    fun getUser(): Pair<String, String>? {
        return if (sharedPreferences.contains("email") && sharedPreferences.contains("password")) {
            Pair(
                sharedPreferences.getString("email", "unknown")!!,
                sharedPreferences.getString("password", "unknown")!!
            )
        } else {
            null
        }
    }

    fun setUser(email: String, password: String) {
        val edit = sharedPreferences.edit()
        edit.putString("email", email)
        edit.putString("password", password)
        edit.apply()
    }

    fun removeUser() {
        if (sharedPreferences.contains("email") && sharedPreferences.contains("password")) {
            val edit = sharedPreferences.edit()
            edit.remove("email")
            edit.remove("password")
            edit.apply()
        }
    }
}