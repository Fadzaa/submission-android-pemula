package com.example.submissiondicodingpemula.model.movie

import com.example.submissiondicodingpemula.model.genre.Genre

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: Any? = null,
    val belongsToCollection: Any? = null,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val imdbID: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Long,
    val voteCount: Long
)
