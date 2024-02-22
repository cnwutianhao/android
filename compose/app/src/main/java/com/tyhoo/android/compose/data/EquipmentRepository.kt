package com.tyhoo.android.compose.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EquipmentRepository @Inject constructor(private val provider: EquipmentListDataProvider) {

    suspend fun getEquipmentList(): LiveData<List<EquipmentResponse>> {
        val equipmentList = MutableLiveData<List<EquipmentResponse>>()
        val list = provider.provideEquipmentListData()
        equipmentList.postValue(list)
        return equipmentList
    }
}