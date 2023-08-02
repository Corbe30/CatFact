package com.example.hiltproject.ViewModel

sealed class CatFactState {
    data class CatFactLoading(val placeholder: String) : CatFactState()

    data class CatFactSuccess(val fact: String) : CatFactState()

    data class CatFactError(val error: String) : CatFactState()
}
