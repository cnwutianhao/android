package com.tyhoo.android.mvvm.data

import com.tyhoo.android.mvvm.api.ApiService
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