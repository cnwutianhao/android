package com.tyhoo.android.compose.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val heroIdName = savedStateHandle.get<String>("heroIdName")
        Log.d("Tyhoo", "heroIdName: $heroIdName")
    }
}