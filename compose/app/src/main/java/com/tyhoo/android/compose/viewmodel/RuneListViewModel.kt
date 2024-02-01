package com.tyhoo.android.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.data.RuneRepository
import com.tyhoo.android.compose.data.RuneResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RuneListViewModel @Inject constructor(
    private val repository: RuneRepository
) : ViewModel() {

    init {
        fetchRuneList()
    }

    private fun fetchRuneList() {
        viewModelScope.launch {
            val result = repository.getRuneList()
            result.observeForever { runeList ->
                _runeList.value = runeList
            }
        }
    }

    private val _runeList = MutableLiveData<List<RuneResponse>>()
    val runeList: LiveData<List<RuneResponse>>
        get() = _runeList
}