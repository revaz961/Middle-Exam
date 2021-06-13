package com.example.exam.bindigAdapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {

    Glide.with(view.context)
        .load(url)
        .error(error)
        .into(view);
}