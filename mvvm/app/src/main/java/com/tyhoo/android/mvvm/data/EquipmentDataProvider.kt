package com.tyhoo.android.mvvm.data

import com.tyhoo.android.mvvm.api.ApiService
import javax.inject.Inject

interface EquipmentListDataProvider {
    suspend fun provideEquipmentListData(): List<EquipmentResponse>
}

class EquipmentListDataProviderImpl @Inject constructor(
    private val service: ApiService
) : EquipmentListDataProvider {
    override suspend fun provideEquipmentListData(): List<EquipmentResponse> {
        return service.equipmentList()
    }
}