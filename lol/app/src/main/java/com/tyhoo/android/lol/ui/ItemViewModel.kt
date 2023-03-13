package com.tyhoo.android.lol.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tyhoo.android.lol.domain.Item

class ItemViewModel : ViewModel() {

    val selectedItem = mutableStateOf<Item?>(null)

    override fun onCleared() {
        super.onCleared()
        selectedItem.value = null
    }
}