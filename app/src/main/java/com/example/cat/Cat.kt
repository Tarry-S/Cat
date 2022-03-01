package com.example.cat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val url : String,
    val artist : String,
    val artist_url : String,
    val source_url : String,
    val error : String
) : Parcelable
