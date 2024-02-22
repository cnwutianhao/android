package com.tyhoo.android.compose.data

import com.tyhoo.android.compose.api.ApiService
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