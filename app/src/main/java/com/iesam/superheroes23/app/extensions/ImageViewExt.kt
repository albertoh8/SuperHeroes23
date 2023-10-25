package com.iesam.superheroes23.app.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url : String){
    Glide
        .with(this.context)
        .load(url)
        .into(this)

}