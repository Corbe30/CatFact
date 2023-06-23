package com.example.hiltproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltproject.DataType.CatFactData
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(val mainRepo: MainRepo) : ViewModel() {
    val catFactMLD : MutableLiveData<List<CatFactData>> by lazy { MutableLiveData<List<CatFactData>>() }

    fun getCatFact(catFactCount: Int) {
        viewModelScope.launch {
            try {
                catFactMLD.value = mainRepo.fetchCatFacts(catFactCount)
            } catch (e: Exception) {
                Log.e("cat fact error: ", "couldn't fetch cat fact :(")
            }
        }
    }

}