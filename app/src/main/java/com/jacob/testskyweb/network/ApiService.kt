package com.jacob.testskyweb.network

import com.jacob.testskyweb.models.FilmModel
import com.jacob.testskyweb.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("v2/list")
    suspend fun getFilms(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<MutableList<FilmModel>>

    @GET()
    suspend fun getWeathers(
        @Url url: String,
        @Query("id") id: Int,
        @Query("appid") appId: String,
        @Query("lang") lang: String,
        @Query("units") units: String
    ): Response<WeatherModel>
}