package com.example.hiltproject

import com.example.hiltproject.DataType.CatFactData
import com.example.hiltproject.DataType.CatFactsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("fact")
    suspend fun getCatFacts() : CatFactData
}