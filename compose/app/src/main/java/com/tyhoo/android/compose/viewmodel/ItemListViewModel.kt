package com.tyhoo.android.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.data.ItemRepository
import com.tyhoo.android.compose.data.ItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    init {
        fetchItemList()
    }

    private fun fetchItemList() {
        viewModelScope.launch {
            val result = repository.getItemList()
            result.observeForever { itemList ->
                _itemList.value = itemList
            }
        }
    }

    private val _itemList = MutableLiveData<List<ItemResponse>>()
    val itemList: LiveData<List<ItemResponse>>
        get() = _itemList
}