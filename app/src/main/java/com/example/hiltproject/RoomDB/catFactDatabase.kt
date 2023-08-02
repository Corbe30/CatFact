package com.example.hiltproject.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatFactTableData::class], version = 2)
abstract class catFactDatabase : RoomDatabase() {
    abstract fun contactDao() : catFactDAO
}