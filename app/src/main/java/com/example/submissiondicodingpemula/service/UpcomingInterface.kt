package com.example.submissiondicodingpemula.service

import com.example.submissiondicodingpemula.model.MovieList
import com.example.submissiondicodingpemula.model.MovieResult

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface UpcomingInterface {
    @GET("movie/upcoming?api_key=2d51650e8cf7b5a2d13b814001a0dd30")
    suspend fun getUpcoming(@Query("api_key") apiKey: String) : Response<MovieList>
}

