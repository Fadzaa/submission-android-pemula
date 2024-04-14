package com.example.submissiondicodingpemula.service

import com.example.submissiondicodingpemula.model.movie.MovieResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface UpcomingInterface {
    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("api_key") apiKey: String) : Response<MovieResponse>
}

