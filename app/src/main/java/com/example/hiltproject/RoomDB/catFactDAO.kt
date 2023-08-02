package com.example.hiltproject.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface catFactDAO {
    @Insert
    suspend fun insertFact(catFact: CatFactTableData)

    @Query("select * from catFactTable")
    fun getFacts() : LiveData<List<CatFactTableData>>

    @Query("SELECT * FROM catFactTable ORDER BY RANDOM() LIMIT 1")
    fun getRandomFact() : LiveData<List<CatFactTableData>>

    @Update
    suspend fun updateFact(catFact: CatFactTableData)

    @Delete
    suspend fun deleteFact(catFact: CatFactTableData)

}