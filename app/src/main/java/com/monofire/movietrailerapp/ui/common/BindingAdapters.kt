package com.monofire.movietrailerapp.ui.common

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.monofire.movietrailerapp.R
import com.monofire.movietrailerapp.util.MOVIE_POSTER_BASE_URL

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:27          │
//└─────────────────────────────┘
@BindingAdapter("app:imgUrl")
fun ImageView.setImageUrl(url: String?) {
    val newUrl = MOVIE_POSTER_BASE_URL + url
    Glide.with(this)
        .load(newUrl)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
        .placeholder(R.drawable.placeholder)
        .into(this)

}

@BindingAdapter("app:textDate")
fun TextView.setTextDate(date: String?) {
   // if (!date.isNullOrEmpty()) text = date.formatDate()
}

@BindingAdapter("app:custom_rating")
fun AppCompatRatingBar.setRatingCount(avarage: Double) {
    rating = avarage.toFloat()
}