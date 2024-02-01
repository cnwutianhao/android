package com.tyhoo.android.compose.data

import com.tyhoo.android.compose.api.ApiService
import javax.inject.Inject

interface RuneListDataProvider {
    suspend fun provideRuneListData(): List<RuneResponse>
}

class RuneListDataProviderImpl @Inject constructor(
    private val service: ApiService
) : RuneListDataProvider {
    override suspend fun provideRuneListData(): List<RuneResponse> {
        return service.runeList()
    }
}