package com.example.happydb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FeelingViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FeelingRepository

    val allFeelings : LiveData<List<Feeling>>

    init {
        val feelingDao = FeelingDatabase.getDatabase(application).feelingDao()
        repository = FeelingRepository(feelingDao)
        allFeelings = repository.allFeelings
    }

    //A coroutine function
    fun insertFeeling(feeling: Feeling) = viewModelScope.launch {
        repository.insertFeeling(feeling)
    }

}