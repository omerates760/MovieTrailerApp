package com.monofire.movietrailerapp.util.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.monofire.movietrailerapp.R

// Code with ❤
//┌─────────────────────────────┐
//│ Created by Ömer ATEŞ        │
//│ ─────────────────────────── │
//│ omerates760@gmail.com       │
//│ ─────────────────────────── │
//│ 26.06.2021 - 10:31          │
//└─────────────────────────────┘
fun Fragment.showErrorMessage() {
    Toast.makeText(
        requireContext(), getString(R.string.query_error_message), Toast.LENGTH_SHORT
    ).show()
}