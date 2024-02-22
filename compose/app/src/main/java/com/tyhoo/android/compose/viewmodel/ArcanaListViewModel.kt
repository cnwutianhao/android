package com.tyhoo.android.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.data.ArcanaRepository
import com.tyhoo.android.compose.data.ArcanaResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArcanaListViewModel @Inject constructor(
    private val repository: ArcanaRepository
) : ViewModel() {

    init {
        fetchArcanaList()
    }

    private fun fetchArcanaList() {
        viewModelScope.launch {
            val result = repository.getArcanaList()
            result.observeForever { arcanaList ->
                _arcanaList.value = arcanaList
            }
        }
    }

    private val _arcanaList = MutableLiveData<List<ArcanaResponse>>()
    val arcanaList: LiveData<List<ArcanaResponse>>
        get() = _arcanaList
}