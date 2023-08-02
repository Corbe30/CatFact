package com.example.hiltproject.ViewModel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltproject.DataType.CatFactData
import com.example.hiltproject.DataType.CatFactUIState
import com.example.hiltproject.MainRepo
import com.example.hiltproject.RoomDB.CatFactTableData
import com.example.hiltproject.RoomDB.catFactDatabase
import com.example.hiltproject.Utils.HashUtils
import javax.inject.Inject
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel @Inject constructor(val mainRepo: MainRepo, val catFactDB: catFactDatabase) : ViewModel() {
    val catFactMLD : MutableLiveData<CatFactUIState> by lazy { MutableLiveData<CatFactUIState>() }

    fun getCatFact() {
        setCatFactMLD(CatFactState.CatFactLoading("ฅ^•ﻌ•^ฅ"))
        viewModelScope.launch {
            try {
                val response = mainRepo.fetchCatFacts()
                if (response != null) {
                    insertIntoDB(response.fact)
                    setCatFactMLD(CatFactState.CatFactSuccess(response.fact))
                }
                else {
                    throw Exception("network error")
                }
            } catch (e: Exception) {
                val dbFact = getRandomFact()
                if (dbFact != null) {
                    setCatFactMLD(CatFactState.CatFactSuccess(dbFact))
                }
                else {
                    setCatFactMLD(CatFactState.CatFactError("Error: $e"))
                }
            }
        }
    }

    suspend fun insertIntoDB(fact: String) {
        catFactDB.contactDao().insertFact(CatFactTableData(
            fact = fact,
            hash = HashUtils.hashString(fact))
        )
    }

    fun getRandomFact() : String? {
        val factList = catFactDB.contactDao().getRandomFact().value
        return factList?.get(0)?.fact
    }

    fun setCatFactMLD(catFactState: CatFactState) {
        catFactMLD.value = when (catFactState) {
            is CatFactState.CatFactLoading -> {
                CatFactUIState(catFactState.placeholder, Color(0xFF5B5F69))
            }
            is CatFactState.CatFactSuccess -> {
                CatFactUIState(catFactState.fact, Color(0xFF12B870))
            }
            is CatFactState.CatFactError -> {
                CatFactUIState(catFactState.error, Color(0xFFF04354))
            }
        }
    }
}