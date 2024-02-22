package com.tyhoo.android.mvvm.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tyhoo.android.mvvm.adapter.EquipmentAdapter
import com.tyhoo.android.mvvm.base.EQUIPMENT_LIST_FIRST_VISIBLE_ITEM_POSITION
import com.tyhoo.android.mvvm.base.EQUIPMENT_LIST_OFFSET
import com.tyhoo.android.mvvm.data.EquipmentRepository
import com.tyhoo.android.mvvm.data.EquipmentResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipmentListViewModel @Inject constructor(
    private val repository: EquipmentRepository
) : ViewModel() {

    fun requestData(
        owner: LifecycleOwner, rcv: RecyclerView, adapter: EquipmentAdapter
    ) = viewModelScope.launch {
        equipmentListObserver(owner, rcv, adapter)
    }

    private suspend fun equipmentListObserver(
        owner: LifecycleOwner,
        rcv: RecyclerView,
        adapter: EquipmentAdapter
    ) = Observer<List<EquipmentResponse>> { equipmentList ->
        adapter.submitList(equipmentList)

        val layoutManager = rcv.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(
            EQUIPMENT_LIST_FIRST_VISIBLE_ITEM_POSITION,
            EQUIPMENT_LIST_OFFSET
        )
    }.apply {
        repository.getEquipmentList().observe(owner, this)
    }
}