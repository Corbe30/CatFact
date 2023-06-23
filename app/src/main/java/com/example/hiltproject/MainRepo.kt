package com.example.hiltproject

import com.example.hiltproject.DataType.CatFactData
import com.example.hiltproject.Model.RetrofitClient
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class MainRepo @Inject constructor(val request: RetrofitServices) {
    suspend fun fetchCatFacts(count: Int) : List<CatFactData> {

//        val retrofit = RetrofitClient().getInstance()
//        val request = retrofit.create(RetrofitServices::class.java)

        return withContext(Dispatchers.IO) {
            val async = async {
                request.getCatFacts(count)
            }
            val catFactListResponse = async.await()
            catFactListResponse.data
        }
    }
}