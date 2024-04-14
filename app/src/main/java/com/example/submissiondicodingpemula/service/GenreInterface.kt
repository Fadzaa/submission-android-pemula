package com.example.submissiondicodingpemula.service

import com.example.submissiondicodingpemula.model.genre.GenreResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreInterface {
    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String) : Response<GenreResponse>
}