package com.example.hiltproject.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catFactTable")
data class CatFactTableData(
    val fact: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val hash: String
)