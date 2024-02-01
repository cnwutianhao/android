package com.tyhoo.android.compose.data

import com.tyhoo.android.compose.api.ApiService
import javax.inject.Inject

interface ItemListDataProvider {
    suspend fun provideItemListData(): List<ItemResponse>
}

class ItemListDataProviderImpl @Inject constructor(
    private val service: ApiService
) : ItemListDataProvider {
    override suspend fun provideItemListData(): List<ItemResponse> {
        return service.itemList()
    }
}