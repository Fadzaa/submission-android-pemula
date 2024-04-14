package com.example.submissiondicodingpemula.service


import com.example.submissiondicodingpemula.model.movie.MovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailInterface {
    @GET("movie/{movieId}")
    suspend fun getDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String)
    : Response<MovieDetail>
}