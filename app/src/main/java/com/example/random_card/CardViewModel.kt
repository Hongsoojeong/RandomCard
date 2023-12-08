package com.example.random_card

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardViewModel: ViewModel() {

    private var _card = MutableLiveData<Int>()
    val card: MutableLiveData<Int>
        get() = _card

    init {
        _card.value = 100
    }

    fun cardNumber(number:Int){
        _card.value = number
    }
}