package com.example.submissiondicodingpemula.service

import com.example.submissiondicodingpemula.model.cast.CastResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastInterface {
    @GET("movie/{movieId}/credits")
    suspend fun getCredits(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<CastResponse>
}