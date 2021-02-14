package com.development.loginmvvm

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout

class Ext {


    companion object {
        // important code for loading image here
        @JvmStatic
        @BindingAdapter("avatar")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            Log.e("imageURL->",imageURL)

            Glide
                .with(imageView.context)
                .load(imageURL)
                .centerCrop()
                .into(imageView);

        }


    }



}