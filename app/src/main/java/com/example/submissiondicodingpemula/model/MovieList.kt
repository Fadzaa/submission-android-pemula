package com.example.submissiondicodingpemula.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieList(
    val title:String,
    val genre:String,
    val duration:String,
    val release:String,
    val overview:String
): Parcelable
