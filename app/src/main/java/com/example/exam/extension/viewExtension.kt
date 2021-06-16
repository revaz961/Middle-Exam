package com.example.exam.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message:String,duration:Int){
    Snackbar.make(this,message,duration).show()
}