package com.example.submissiondicodingpemula.service

import com.example.submissiondicodingpemula.model.Genre
import com.example.submissiondicodingpemula.model.GenreResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreInterface {
    @GET("genre/movie/list?api_key=2d51650e8cf7b5a2d13b814001a0dd30")
    suspend fun getGenres(@Query("api_key") apiKey: String) : Response<GenreResponse>
}