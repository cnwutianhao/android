package com.tyhoo.android.compose.data

import com.tyhoo.android.compose.api.ApiService
import javax.inject.Inject

interface ArcanaListDataProvider {
    suspend fun provideArcanaListData(): List<ArcanaResponse>
}

class ArcanaListDataProviderImpl @Inject constructor(
    private val service: ApiService
) : ArcanaListDataProvider {
    override suspend fun provideArcanaListData(): List<ArcanaResponse> {
        return service.arcanaList()
    }
}