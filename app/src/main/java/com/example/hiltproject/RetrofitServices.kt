package com.example.hiltproject

import com.example.hiltproject.DataType.CatFactsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("facts")
    suspend fun getCatFacts(@Query("limit") limit: Int) : CatFactsResponse
}