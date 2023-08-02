package com.example.hiltproject.Model

import android.content.Context
import androidx.room.Room
import com.example.hiltproject.RetrofitServices
import com.example.hiltproject.RoomDB.catFactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    @Singleton
    fun provideCatFactDatabase(@ApplicationContext context: Context): catFactDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            catFactDatabase::class.java,
            "catFactDB"
        ).fallbackToDestructiveMigration().build()
    }

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