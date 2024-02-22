package com.tyhoo.android.compose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyhoo.android.compose.data.EquipmentRepository
import com.tyhoo.android.compose.data.EquipmentResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentListViewModel @Inject constructor(
    private val repository: EquipmentRepository
) : ViewModel() {

    init {
        fetchEquipmentList()
    }

    private fun fetchEquipmentList() {
        viewModelScope.launch {
            val result = repository.getEquipmentList()
            result.observeForever { equipmentList ->
                _equipmentList.value = equipmentList
            }
        }
    }

    private val _equipmentList = MutableLiveData<List<EquipmentResponse>>()
    val equipmentList: LiveData<List<EquipmentResponse>>
        get() = _equipmentList
}