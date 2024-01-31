package com.tyhoo.android.compose.data

import com.tyhoo.android.compose.api.ApiService
import javax.inject.Inject

interface HeroListDataProvider {
    suspend fun provideHeroListData(): List<HeroResponse>
}

class HeroListDataProviderImpl @Inject constructor(
    private val service: ApiService
) : HeroListDataProvider {
    override suspend fun provideHeroListData(): List<HeroResponse> {
        return service.heroList()
    }
}