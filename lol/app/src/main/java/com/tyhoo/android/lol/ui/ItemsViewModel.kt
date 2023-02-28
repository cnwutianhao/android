package com.tyhoo.android.lol.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.lol.Result
import com.tyhoo.android.lol.domain.Item
import com.tyhoo.android.lol.domain.ItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val useCase: ItemsUseCase
) : ViewModel() {

    private val _items = mutableStateOf(emptyList<Item>())
    val items: State<List<Item>> = _items

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val _version = mutableStateOf<String?>(null)
    val version: State<String?> = _version

    private val _fileTime = mutableStateOf<String?>(null)
    val fileTime: State<String?> = _fileTime

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = useCase.invoke()) {
                    is Result.Success -> {
                        _items.value = result.data.items
                        _version.value = result.data.version
                        _fileTime.value = result.data.fileTime
                    }
                    is Result.Error -> {
                        _error.value = result.exception.message
                    }
                }
            }
        }
    }
}