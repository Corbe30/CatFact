package com.example.hiltproject.Model

import com.example.hiltproject.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    @Named("catFactRetrofit")
    fun providesCatFactRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Named("dogFaceRetrofit")
    fun providesDogFaceRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesRetrofitServices(@Named("dogFaceRetrofit") retrofit: Retrofit) : RetrofitServices {
        return retrofit.create(RetrofitServices::class.java)
    }
}