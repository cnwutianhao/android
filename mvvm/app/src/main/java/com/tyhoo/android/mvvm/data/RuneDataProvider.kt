package com.tyhoo.android.mvvm.data

import com.tyhoo.android.mvvm.api.ApiService
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