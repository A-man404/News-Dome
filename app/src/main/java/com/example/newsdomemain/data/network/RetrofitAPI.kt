package com.example.newsdomemain.data.network

import com.example.newsdomemain.data.model.Model
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val retrofitService = retrofit.create(RetrofitAPI::class.java)

interface RetrofitAPI {
    @GET("everything")
    suspend fun getList(
        @Query("q") query: String,
        @Query("apikey") apiKey: String = Constants.API_KEY
    ): Model
}